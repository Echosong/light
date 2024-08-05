package cn.light.generator.processor;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.light.common.anno.*;
import cn.light.common.dto.ListColumnDTO;
import cn.light.common.dto.PageInfo;
import cn.light.common.enums.BaseEnum;
import cn.light.common.enums.CodeTypeEnum;
import cn.light.common.enums.HtmlTypeEnum;
import cn.light.generator.config.Const;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>Title: 处理前端列表生成</p >
 * <p>Description: 处理前端列表生成</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0411:48</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Slf4j
public class ViewService extends BaseService implements ServiceInterface {

    ViewService(Class<?> clazz) {
        super(clazz);
    }

    String vuePath = "";
    //表格最大列数大于等于12 时候，操作需要固定
     final int maxColumnCount = 10;

    @Override
    public void start() {
        vuePath = Const.VUE_PATH + "/" + StrUtil.lowerFirst(this.className);
        AutoEntity autoEntity = clazz.getAnnotation(AutoEntity.class);

        Field[] declaredFields = clazz.getDeclaredFields();
        Class<?> superclass = clazz.getSuperclass();
        if(!Objects.equals(superclass.getSimpleName(), "SysBase")){
            declaredFields = ArrayUtil.addAll(declaredFields, superclass.getDeclaredFields());
        }

        if (autoEntity.viewFrom()) {
            this.listView(declaredFields);
        }
        if (autoEntity.viewList()) {
            this.viewFrom(declaredFields);
            if(autoEntity.viewInfo()){
                //this.viewInfo(declaredFields);
            }
        }
    }

    private static final String  START_WITH = "{javax";
    private static final String START_ORG = "{org";
    private static final String BASE_ENUM = "BaseEnum";



    /**
     * 生成列表
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    private void listView(Field[] declaredFields) {
        String templateFile = this.templatePath + "list.vue";
        String listPath = vuePath + "/list.vue";
        if (FileUtil.isFile(listPath)) {
            if(reWrite(listPath, CodeTypeEnum.LIST_VIEW)){
                return;
            }
        }
        String tplContent = this.replaceTpl(templateFile);
        List<String> paramsList = new ArrayList<>();
        List<String> fromStr = new ArrayList<>();
        List<ListColumnDTO> tableColumns = new ArrayList<>();
        Set<String> importFiles = new HashSet<>();

        //追加一个id编号
        int columnCount = 1;
        for (Field field : declaredFields) {
            if (!field.isAnnotationPresent(AutoEntityField.class)) {
                continue;
            }
            AutoEntityField autoEntityField = field.getAnnotation(AutoEntityField.class);
            if (field.isAnnotationPresent(InQueryDTO.class)) {
                if ("Date".equals(field.getType().getSimpleName())) {
                    paramsList.add(StrUtil.format("start{}:''", StrUtil.upperFirst(field.getName())));
                    paramsList.add(StrUtil.format("end{}:''", StrUtil.upperFirst(field.getName())));
                } else {
                    paramsList.add(StrUtil.format("{}:''", field.getName()));
                }
                fromStr.add(this.elForm(field, autoEntityField));
            }
            if (!field.isAnnotationPresent(NotinListDTO.class)) {
                tableColumns.add(this.elTableColumn(field, autoEntityField));
                columnCount++;
            }
            if (autoEntityField.htmlType() == HtmlTypeEnum.UPLOAD) {
                importFiles.add("import Preview from \"/@/components/framework/base-preview-image/index.vue\";");
            }else if (autoEntityField.htmlType() == HtmlTypeEnum.FILE) {
                importFiles.add("import Link from \"/@/components/framework/base-preview-file/index.vue\";");
            }else if(autoEntityField.htmlType() == HtmlTypeEnum.RADIO){
                importFiles.add("import ESwitch from \"/@/components/framework/base-switch/index.vue\";");
            }else if (autoEntityField.htmlType() == HtmlTypeEnum.SELECT){
                importFiles.add("import selectData from '/@/components/framework/base-map/index.vue'");
            }
        }
        PageInfo pageInfo = new PageInfo();
        String queryParam = StrUtil.format("pageSize:{},page:{}, total: 0", pageInfo.getPageSize(), pageInfo.getPage());
        if (paramsList.isEmpty()) {
            queryParam = "{" + queryParam + "}";
        } else {
            queryParam = "{" + queryParam + ", " + String.join(",", paramsList) + "}";
        }

        String elContent = String.join("\r\n", fromStr);
        tplContent = StrUtil.replace(tplContent, "#{a-form-item}#", elContent);
        //处理枚举下来组件
        if (elContent.contains("<input-enum")) {
            importFiles.add("import InputEnum from \"/@/components/framework/base-enum/index.vue\";");
        }
        if(columnCount >= maxColumnCount){
            tplContent = StrUtil.replaceIgnoreCase(tplContent,"#{fixed}#", "fixed=\"right\"");
        }else{
            tplContent = StrUtil.replaceIgnoreCase(tplContent,"#{fixed}#","");
        }

        tplContent = StrUtil.replace(tplContent, "#{queryPageParams}#", queryParam);
        ListColumnDTO listColumn = new ListColumnDTO();
        listColumn.setTitle("操作");
        listColumn.setWidth(90);
        listColumn.setFixed("right");
        listColumn.setDataIndex("action");
        tableColumns.add(listColumn);

        tplContent = StrUtil.replace(tplContent, "#{listColumns}#", JSONUtil.toJsonStr(tableColumns));
        tplContent = StrUtil.replace(tplContent, "//importFiles", String.join("\r\n", importFiles));

        FileUtil.writeString(tplContent, listPath, Charset.defaultCharset());
    }



    /**
     * 处理表格行信息
     *
     * @param field 字段
     * @return 处理后字符
     */
    private ListColumnDTO elTableColumn(Field field, AutoEntityField autoEntityField) {
        String typeName = field.getName();
        ListColumnDTO listColumn = new ListColumnDTO();
        try {
            if (!BASE_ENUM.equals(autoEntityField.enums().getSimpleName())) {
                typeName = typeName + "Enum";
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        listColumn.setDataIndex(typeName);
        listColumn.setTitle(autoEntityField.value());
        listColumn.setEllipsis(true);
        return listColumn;
    }


    /**
     * 处理列表查询信息
     *
     * @param field 字段
     * @param autoEntityField 注解解析
     * @return 表单字符
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    private String elForm(Field field, AutoEntityField autoEntityField) {
        String returnValue = "";
        if ("String".equals(field.getType().getSimpleName())) {
            returnValue = StrUtil.format("""
                    <a-form-item label="{}" v-if="!query.{}" class="smart-query-form-item">
                     <a-input v-model:value="p.{}" placeholder="模糊查询"></a-input>
                    </a-form-item>""", autoEntityField.value(), field.getName(),field.getName());
        }
        if ("Date".equals(field.getType().getSimpleName())) {
            returnValue = StrUtil.format("""
                            <a-form-item label="{}"  v-if="!query.{}" class="smart-query-form-item">
                               <a-range-picker @change="(rangDate)=> {p.start{} = rangDate[0];p.end{} = rangDate[1];}" value-format="YYYY-MM-DD HH:mm:ss" />
                            </a-form-item>""", autoEntityField.value(),field.getName(), StrUtil.upperFirst(field.getName()),
                    StrUtil.upperFirst(field.getName()));
        }

        if (!BASE_ENUM.equals(autoEntityField.enums().getSimpleName())) {
            Class<? extends BaseEnum> lclazz = autoEntityField.enums();

            returnValue = StrUtil.format("""
                            <a-form-item label="{}"  v-if="!query.{}" class="smart-query-form-item">
                                  <input-enum enumName="{}"  style="width: 180px;"  v-model="p.{}"></input-enum>
                            </a-form-item>""",
                    autoEntityField.value(), field.getName(),StrUtil.lowerFirst(lclazz.getSimpleName()), field.getName());
        }

        if(autoEntityField.htmlType() == HtmlTypeEnum.SELECT){
            returnValue = StrUtil.format("""
                            <a-form-item label="{}"  v-if="!query.{}" class="smart-query-form-item">
                             <select-data v-model:value="p.{}" routeName="{}" ></select-data>
                            </a-form-item>""",
                    autoEntityField.value(), field.getName(), field.getName(), StrUtil.lowerFirst(autoEntityField.source()));
        }
        return returnValue;
    }

    private boolean reWrite(String  listPath, CodeTypeEnum codeTypeEnum){
        boolean autoCover = clazz.isAnnotationPresent(AutoCover.class);
        if(!autoCover){
            return true;
        }
        AutoCover annotation = clazz.getAnnotation(AutoCover.class);
        if( !ArrayUtil.containsAny(annotation.value(), codeTypeEnum, CodeTypeEnum.ALL)){
            return true;
        }

        //进行备份
        FileUtil.copy(listPath, listPath.replace(".vue"
                        , StrUtil.format("_{}.txt", DateUtil.format(LocalDateTime.now(),"yyyMMddHHmmss")))
                ,true);
        return false;
    }

    private String getFromUploadFileStr(HtmlTypeEnum type,String fieldName) {
        String componentName = type == HtmlTypeEnum.UPLOAD?"FileImage":"File";
        Map<String,String> map = new HashMap<>();
        map.put("componentName", componentName);
        map.put("fieldName", fieldName);
        String fromUploadFileStr = " <{componentName} @onremove=\"m.{fieldName} = ''\" @onSuccess=\"m.{fieldName} = $event\" :file=\"m.{fieldName}\" />";
        return  StrUtil.format(fromUploadFileStr, map);
    }

    private void viewInfo(Field[] declaredFields){
        String templateFile = this.templatePath + "info.vue";

        String listPath = vuePath + "/info.vue";
        if (FileUtil.isFile(listPath)) {
            if(reWrite(listPath, CodeTypeEnum.INFO_VIEW)){
                return;
            }
        }
        String tplContent = this.replaceTpl(templateFile);
        List<String> infoList = new ArrayList<>();

        String infoTemplate = """
                <a-descriptions-item>
                 <template #label>
                        <div class="cell-item" style="width:100px;">
                          {}
                        </div>
                      </template>
                      {}
                </a-descriptions-item>
                """;

        for (Field field : declaredFields) {
            if (!field.isAnnotationPresent(AutoEntityField.class)) {
                continue;
            }
            AutoEntityField annotation = field.getAnnotation(AutoEntityField.class);
            String typeName = field.getName();
            try {
                if (!BASE_ENUM.equals(annotation.enums().getSimpleName())) {
                    typeName = typeName + "Enum";
                }
            } catch (Exception e) {
                log.info(e.getMessage());
            }

            String currentHtml = " <div style=\"width:500px;\" v-html=\"one.{}\"></div>";
            if(annotation.htmlType() == HtmlTypeEnum.UPLOAD){
                currentHtml = """
                            <img :src="one.{}" style="width:100px; height:100px" />
                        """;
            }
            currentHtml = StrUtil.format(currentHtml, typeName);
            infoList.add(StrUtil.format(infoTemplate, annotation.value(), currentHtml));

        }
        tplContent = tplContent.replace("#{a-descriptions-item}#", StrUtil.join( "", infoList));
        FileUtil.writeString(tplContent, listPath, Charset.defaultCharset());
    }

    /**
     * 编辑
     */
    @SuppressWarnings("AlibabaMethodTooLong")
    private void viewFrom(Field[] declaredFields) {
        String templateFile = this.templatePath + "add.vue";

        String listPath = vuePath + "/add.vue";
        if (FileUtil.isFile(listPath)) {
            if(reWrite(listPath, CodeTypeEnum.ADD_VIEW)){
                return;
            }
        }

        String tplContent = this.replaceTpl(templateFile);
        List<String> elFormItems = new ArrayList<>();
        List<String> ms = new ArrayList<>();
        List<String> rulesFields = new ArrayList<>();
        List<String> textEdits = new LinkedList<>();
        Set<String> importFiles = new HashSet<>();

        for (Field field : declaredFields) {
            if (!field.isAnnotationPresent(AutoEntityField.class)) {
                continue;
            }
            AutoEntityField annotation = field.getAnnotation(AutoEntityField.class);
            rulesFields.add(this.validationRules(field));

            if ("String".equals(field.getType().getSimpleName()) || "Date".equals(field.getType().getSimpleName())) {
                ms.add(StrUtil.format("{}:''", field.getName()));
            } else {
                ms.add(StrUtil.format("{}:0", field.getName()));
            }

            String tipMsg = "";
            if(annotation.notes().length() > 4){
                tipMsg = StrUtil.format("tooltip=\"{}\"",  annotation.notes());
            }

            String formItem = "";

            if (annotation.htmlType() != HtmlTypeEnum.INPUT) {
                String htmlTypeStr = "";

                switch (annotation.htmlType()) {
                    case RADIO:
                        htmlTypeStr = StrUtil.format("<e-switch v-model:value=\"m.{}\" ></e-switch>", StrUtil.lowerFirst(field.getName()));
                        break;
                    case SELECT:
                        htmlTypeStr = StrUtil.format(" <select-data v-model:value=\"m.{}\" routeName=\"{}\" ></select-data>", StrUtil.lowerFirst(field.getName()),StrUtil.lowerFirst(annotation.source()));
                        importFiles.add("import selectData from '/@/components/framework/base-map/index.vue'");
                        break;
                    case TEXTAREA:
                        htmlTypeStr = StrUtil.format("<a-textarea   rows=\"2\" placeholder=\"{}\"  v-model:value=\"m.{}\">" +
                                "</a-textarea>", annotation.value(), StrUtil.lowerFirst(field.getName()));
                        break;
                    case UPLOAD:
                    case FILE:
                        //暂时一个页面只考虑一个上传
                        htmlTypeStr = getFromUploadFileStr(annotation.htmlType(), field.getName());
                        importFiles.add(annotation.htmlType() == HtmlTypeEnum.UPLOAD? "import FileImage from \"/@/components/framework/base-upload-image/index.vue\";" : "import File from \"/@/components/framework/base-upload-file/index.vue\";");
                        break;
                    case TEXTEDIT:
                        htmlTypeStr = StrUtil.format( "<Wangeditor ref=\"{}\" :modelValue=\"m.{}\"></Wangeditor> ", field.getName(), StrUtil.lowerFirst(field.getName()));
                        textEdits.add(field.getName());
                        break;
                    default:
                }
                formItem = (StrUtil.format("""
                        <a-form-item label="{}" v-if="!query.{}" ##>
                        {}
                        </a-form-item>""",
                        annotation.value(), field.getName(), htmlTypeStr));

                elFormItems.add(StrUtil.replace(formItem, "##", tipMsg));
                continue;
            }

            if (!"BaseEnum".equals(annotation.enums().getSimpleName())) {
                importFiles.add("import InputEnum from \"/@/components/framework/base-enum/index.vue\";");
                Class<? extends BaseEnum> lclazz = annotation.enums();
                formItem = (StrUtil.format("""
                                <a-form-item label="{}" v-if="!query.{}" ##>
                                     <input-enum enumName="{}" v-model:value="m.{}" ></input-enum>
                                </a-form-item>""", annotation.value(), field.getName(), StrUtil.lowerFirst(lclazz.getSimpleName()),
                        field.getName()));

                elFormItems.add(StrUtil.replace(formItem, "##", tipMsg));

                continue;
            }

            if ("Date".equals(field.getType().getSimpleName())) {
                formItem = (StrUtil.format("""
                      <a-form-item label="{}："  prop="{}" v-if="!query.{}" ##>
                         <a-date-picker v-model:value="m.{}" type="datetime" value-format="YYYY-MM-DD HH:mm:ss" placeholder="{}"></a-date-picker>
                      </a-form-item>""", annotation.value(), field.getName(),field.getName(), field.getName(), annotation.value()));
            } else {
                formItem = (StrUtil.format("""
                        <a-form-item label="{}"  prop="{}" v-if="!query.{}" ##>
                            <a-input v-model:value="m.{}"></a-input>
                        </a-form-item>""", annotation.value(), field.getName(),field.getName(), field.getName()));
            }
            elFormItems.add(StrUtil.replace(formItem, "##", tipMsg));
        }
        //
        String elContent = String.join("\r\n", elFormItems);
        if(!textEdits.isEmpty()){
            List<String> importEdits = new ArrayList<>();
            List<String> replaceEdits = new ArrayList<>();
            importFiles.add("import Wangeditor from '/@/components/framework/wangeditor/index.vue';");
            for (String textEdit : textEdits) {
                importEdits.add(StrUtil.format("const {} = ref()", textEdit));
                String getEditContent = """
                            let {content}Text = {content}.value.getHtml()
                            if({content}Text){
                                m.value.{content} = {content}Text
                            }
                        """;
                Map<String,String> mapContent = new HashMap<>(){{put("content", textEdit);}};
                replaceEdits.add(StrUtil.format(getEditContent, mapContent));
            }
            tplContent =  StrUtil.replaceIgnoreCase(tplContent,"//create_editor", String.join("\r\n", importEdits));
            //replace_editor
            tplContent =  StrUtil.replaceIgnoreCase(tplContent,"//replace_editor", String.join("\r\n", replaceEdits));
        }else{
            tplContent = StrUtil.replaceIgnoreCase(tplContent,"//create_editor", "");
            tplContent = StrUtil.replaceIgnoreCase(tplContent,"//replace_editor", "");
        }

        tplContent = StrUtil.replace(tplContent, "//rule_fields", String.join("\r\n", rulesFields));
        tplContent = StrUtil.replace(tplContent, "#{a-form-item}#", elContent);
        tplContent = StrUtil.replace(tplContent, "//data_init","{"+ String.join(",\r\n", ms)+"}");
        tplContent = StrUtil.replace(tplContent, "//import_file", String.join("\r\n", importFiles));
        FileUtil.writeString(tplContent, listPath, Charset.defaultCharset());
       // this.reWriteConfigJs("add");
    }

    /**
     * 处理前端验证规则
     *
     * @param field 字段
     * return
     * NotNull 被注释的元素必须不为 null
     * Size(max=, min=)   被注释的元素的大小必须在指定的范围内
     * Pattern(regex=,flag=) 被注释的元素必须符合指定的正则表达式
     * NotBlank(message =)   验证字符串非null，且长度必须大于0
     * Email 被注释的元素必须是电子邮箱地址
     * Length(min=,max=) 被注释的字符串的大小必须在指定的范围内
     * Range(min=,max=,message=) 被注释的元素必须在合适的范围内
     */
    private String validationRules(Field field) {
        StringBuilder ruleBuilder = new StringBuilder();
        if(!field.isAnnotationPresent(AutoEntityField.class)){
            return null;
        }
        AutoEntityField autoEntityField = field.getAnnotation(AutoEntityField.class);
        String ruleField = "{}:[{}],";
        String message;
        if (field.isAnnotationPresent(NotNull.class)) {
            ruleBuilder.append(StrUtil.format(" {required: true, message: '请输入{}', trigger: 'blur' },",
                  autoEntityField.value()));
        }
        if(field.isAnnotationPresent(Length.class)){
            Length lenAnne = field.getAnnotation(Length.class);
            message = lenAnne.message();
            if(message.startsWith(START_ORG)){
               message = StrUtil.format("长度在 {} 到 {} 个字符", lenAnne.min(), lenAnne.max());
            }
            ruleBuilder.append(StrUtil.format("{ min: {}, max: {}, message: '{}', trigger: 'blur' },",
                    lenAnne.min(), lenAnne.max(), message));
        }
        if(field.isAnnotationPresent(Email.class)){
            Email email = field.getAnnotation(Email.class);
            message = email.message();
            if(message.startsWith(START_WITH)){
                message = StrUtil.format("请输入正确的邮箱地址");
            }
            ruleBuilder.append(StrUtil.format("{ type: 'email', message: '{}', trigger: ['blur', 'change'] }",message));
        }
        if(field.isAnnotationPresent(Range.class)){
            Range range = field.getAnnotation(Range.class);
            ruleBuilder.append(StrUtil.format("{pattern:/^\\d{{},}$/, message:'{}必须为数字类型', trigger: 'blur'},",range.min(), autoEntityField.value()));
        }

        if(field.isAnnotationPresent(Pattern.class)){
            Pattern pattern = field.getAnnotation(Pattern.class);
            message = pattern.message();
            if(message.startsWith(START_WITH)){
                message = StrUtil.format("输入符合规则的{}", autoEntityField.value());
            }
            ruleBuilder.append(StrUtil.format("{pattern:/{}/, message:'', trigger: 'blur'}", pattern.regexp(), message));
        }
        return StrUtil.format(ruleField,field.getName(), ruleBuilder.toString());
    }
}


