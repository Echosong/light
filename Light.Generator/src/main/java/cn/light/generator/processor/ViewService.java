package cn.light.generator.processor;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.light.common.anno.*;
import cn.light.common.dto.PageInfo;
import cn.light.common.enums.BaseEnum;
import cn.light.common.enums.CodeTypeEnum;
import cn.light.common.enums.HtmlTypeEnum;
import cn.light.generator.Const;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
        if (autoEntity.viewFrom()) {
            this.listView();
        }
        if (autoEntity.viewList()) {
            this.viewFrom();
        }
    }

    private static final String  START_WITH = "{javax";
    private static final String START_ORG = "{org";
    private static final String BASE_ENUM = "BaseEnum";

    /**
     * 写入配置文件
     *
     * @param typeStr 类型字符
     */
    private void reWriteConfigJs(String typeStr) {
        Map<String, String> map = new HashMap<>(12);
        map.put("name", StrUtil.lowerFirst(this.className));
        map.put("type", typeStr);
        String perms = StrUtil.format("{perms: \"{name}-{type}\", view: () => import('@/views/{name}/{type}.vue')},",
                map);
        List<String> strings = FileUtil.readLines(Const.VUE_ROOT_ROUTER, Charset.defaultCharset());
        Set<String> setLine = new HashSet<>();

        for (String string : strings) {
            string = StrUtil.trim(string);
            if (!string.startsWith("export default [") && !string.startsWith("]")) {
                setLine.add(StrUtil.trim(string));
            }
        }
        setLine.add(perms);

        List<String> collect = new ArrayList<>();
        collect.add("export default [");
        collect.addAll(setLine);
        collect.add("];");

        FileUtil.writeLines(collect, Const.VUE_ROOT_ROUTER, Charset.defaultCharset());
    }


    /**
     * 生成列表
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    private void listView() {
        String templateFile = this.templatePath + "list.vue";
        String listPath = vuePath + "/list.vue";
        if (FileUtil.isFile(listPath)) {
            if(reWrite(listPath, CodeTypeEnum.LIST_VIEW)){
                return;
            }
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        String tplContent = this.replaceTpl(templateFile);
        List<String> paramsList = new ArrayList<>();
        List<String> fromStr = new ArrayList<>();
        List<String> tableColumns = new ArrayList<>();
        Set<String> importFiles = new HashSet<>();

        //追加一个id编号
        tableColumns.add("<el-table-column type=\"selection\"></el-table-column>");
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
                importFiles.add("import Preview from \"@/components/file/preview.vue\";");
            }else if (autoEntityField.htmlType() == HtmlTypeEnum.FILE) {
                importFiles.add("import Link from \"@/components/file/link.vue\";");
            }else if(autoEntityField.htmlType() == HtmlTypeEnum.RADIO){
                importFiles.add("import ElSwitch from \"@/components/ESwitch/ESwitch.vue\";");
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
        tplContent = StrUtil.replace(tplContent, "#{el-form-item}#", elContent);
        //处理枚举下来组件
        if (elContent.contains("<input-enum")) {
            importFiles.add("import InputEnum from \"@/components/enum/InputEnum.vue\";");
        }
        if(columnCount >= maxColumnCount){
            tplContent = StrUtil.replaceIgnoreCase(tplContent,"#{fixed}#", "fixed=\"right\"");
        }else{
            tplContent = StrUtil.replaceIgnoreCase(tplContent,"#{fixed}#","");
        }

        tplContent = StrUtil.replace(tplContent, "#{queryPageParams}#", queryParam);
        tplContent = StrUtil.replace(tplContent, "#{el-table-column}#",
                String.join("\r\n", tableColumns));
        tplContent = StrUtil.replace(tplContent, "//importFiles", String.join("\r\n", importFiles));

        FileUtil.writeString(tplContent, listPath, Charset.defaultCharset());
        this.reWriteConfigJs("list");
    }



    /**
     * 处理表格行信息
     *
     * @param field 字段
     * @return 处理后字符
     */
    private String elTableColumn(Field field, AutoEntityField autoEntityField) {
        String returnValue;
        String typeName = field.getName();
        try {
            if (!BASE_ENUM.equals(autoEntityField.enums().getSimpleName())) {
                typeName = typeName + "Enum";
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        if (autoEntityField.htmlType() == HtmlTypeEnum.UPLOAD) {
            String uploadListTemplate = """
                     <el-table-column  label="{}"  #{sortable}# >
                         <template #default="s">
                             <Preview :imgUrl="s.row.{}"></Preview>
                         </template>
                     </el-table-column>
                    """;
            returnValue = StrUtil.format(uploadListTemplate, autoEntityField.value(), typeName);
        } else if(autoEntityField.htmlType() == HtmlTypeEnum.FILE) {
            String fileTemplate = """
                     <el-table-column  label="{}"  #{sortable}# >
                         <template #default="s">
                             <Link :fileUrl="s.row.{}"></Link>
                         </template>
                     </el-table-column>
                    """;
            returnValue =  StrUtil.format(fileTemplate, autoEntityField.value(), typeName);
        }else if(autoEntityField.htmlType() == HtmlTypeEnum.RADIO){
            String radioTemplate = """
                     <el-table-column  label="{}"  #{sortable}# >
                         <template #default="s">
                              <ElSwitch v-model="s.row.{}" @change="updateSwitch(s.row)"></ElSwitch>
                         </template>
                     </el-table-column>
                    """;
            returnValue =  StrUtil.format(radioTemplate, autoEntityField.value(), field.getName());
        } else {
            returnValue = StrUtil.format("  <el-table-column  label=\"{}\" #{sortable}# #{overflow}# prop=\"{}\" ></el-table-column>",
                    autoEntityField.value(), typeName);
        }
        if (field.isAnnotationPresent(AutoSorted.class)) {
            returnValue =  returnValue.replace("#{sortable}#", "sortable");
        }else{
            returnValue =  returnValue.replace("#{sortable}#", "");
        }
        //:show-overflow-tooltip="true"
        if (autoEntityField.htmlType() == HtmlTypeEnum.TEXTEDIT || autoEntityField.htmlType() == HtmlTypeEnum.TEXTAREA) {
            returnValue =  returnValue.replace("#{overflow}#", ":show-overflow-tooltip=\"true\"") ;
        }else {
            returnValue = returnValue.replace("#{overflow}#", "");
        }
        return returnValue;
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
                    <el-form-item label="{}">
                     <el-input v-model="p.{}" placeholder="模糊查询"></el-input>
                    </el-form-item>""", autoEntityField.value(), field.getName());
        }
        if ("Date".equals(field.getType().getSimpleName())) {
            returnValue = StrUtil.format("""
                            <el-form-item label="{}">
                                      <el-date-picker
                                        v-model="p.start{}"
                                        type="datetime"
                                        value-format="yyyy-MM-dd HH:mm:ss"
                                        placeholder="开始日期"></el-date-picker>
                                      -
                                      <el-date-picker
                                        v-model="p.end{}"
                                        type="datetime"
                                        value-format="yyyy-MM-dd HH:mm:ss"
                                        placeholder="结束日期"></el-date-picker>
                                    </el-form-item>""", autoEntityField.value(), StrUtil.upperFirst(field.getName()),
                    StrUtil.upperFirst(field.getName()));
        }

        if (!BASE_ENUM.equals(autoEntityField.enums().getSimpleName())) {
            Class<? extends BaseEnum> lclazz = autoEntityField.enums();

            returnValue = StrUtil.format("""
                            <el-form-item label="{}">
                                  <input-enum enumName="{}"  v-model="p.{}"></input-enum>
                            </el-form-item>""",
                    autoEntityField.value(), StrUtil.lowerFirst(lclazz.getSimpleName()), field.getName());
        }
        return returnValue;
    }

    private boolean reWrite(String  listPath, CodeTypeEnum codeTypeEnum){
        boolean autoCover = clazz.isAnnotationPresent(AutoCover.class);
        if(!autoCover){
            return true;
        }
        AutoCover annotation = clazz.getAnnotation(AutoCover.class);
        if( !ArrayUtil.contains(annotation.value(), codeTypeEnum)){
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

    /**
     * 编辑
     */
    @SuppressWarnings("AlibabaMethodTooLong")
    private void viewFrom() {
        String templateFile = this.templatePath + "add.vue";

        String listPath = vuePath + "/add.vue";
        if (FileUtil.isFile(listPath)) {
            if(reWrite(listPath, CodeTypeEnum.ADD_VIEW)){
                return;
            }
        }

        String tplContent = this.replaceTpl(templateFile);
        Field[] declaredFields = clazz.getDeclaredFields();
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

            if (annotation.htmlType() != HtmlTypeEnum.INPUT) {
                String htmlTypeStr = "";

                switch (annotation.htmlType()) {
                    case RADIO:
                        htmlTypeStr = StrUtil.format("<el-switch v-model=\"m.{}\" ></el-switch>", StrUtil.lowerFirst(field.getName()));
                        break;
                    case TEXTAREA:
                        htmlTypeStr = StrUtil.format("<el-input type=\"textarea\"  rows=\"2\" placeholder=\"{}\"  v-model=\"m.{}\">" +
                                "</el-input>", annotation.value(), StrUtil.lowerFirst(field.getName()));
                        break;
                    case UPLOAD:
                    case FILE:
                        //暂时一个页面只考虑一个上传
                        htmlTypeStr = getFromUploadFileStr(annotation.htmlType(), field.getName());
                        importFiles.add(annotation.htmlType() == HtmlTypeEnum.UPLOAD? "import FileImage from \"@/components/file/fileImage.vue\";" : "import File from \"@/components/file/file.vue\";");
                        break;
                    case TEXTEDIT:
                        htmlTypeStr = StrUtil.format( "<Tinymce ref=\"{}\" v-model=\"m.{}\"></Tinymce> ", field.getName(), StrUtil.lowerFirst(field.getName()));
                        textEdits.add(field.getName());
                        break;
                    default:
                }
                elFormItems.add(StrUtil.format("<el-form-item label=\"{}\">\n" +
                        "{}</el-form-item>", annotation.value(), htmlTypeStr));

                continue;
            }

            if (!"BaseEnum".equals(annotation.enums().getSimpleName())) {
                importFiles.add("import InputEnum from \"@/components/enum/InputEnum.vue\";");
                Class<? extends BaseEnum> lclazz = annotation.enums();
                elFormItems.add(StrUtil.format("""
                                <el-form-item label="{}">
                                     <input-enum enumName="{}" v-model="m.{}" ></input-enum>
                                </el-form-item>""", annotation.value(), StrUtil.lowerFirst(lclazz.getSimpleName()),
                        field.getName()));
                continue;
            }

            if ("Date".equals(field.getType().getSimpleName())) {
                elFormItems.add(StrUtil.format("""
                      <el-form-item label="{}："  prop="{}" >
                         <el-date-picker v-model="m.{}" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="{}"></el-date-picker>
                      </el-form-item>""", annotation.value(), field.getName(), field.getName(), annotation.value()));
            } else {
                elFormItems.add(StrUtil.format("""
                        <el-form-item label="{}："  prop="{}" >
                            <el-input v-model="m.{}"></el-input>
                        </el-form-item>""", annotation.value(), field.getName(), field.getName()));
            }
        }
        //
        String elContent = String.join("\r\n", elFormItems);
        if(!textEdits.isEmpty()){
            List<String> importEdits = new ArrayList<>();
            List<String> replaceEdits = new ArrayList<>();
            importFiles.add("import Tinymce from '@/components/Tinymce/Tinymce.vue';");
            for (String textEdit : textEdits) {
                importEdits.add(StrUtil.format("const {} = ref()", textEdit));
                String getEditContent = """
                            let {content}Text = {content}.value.getContent()
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
        tplContent = StrUtil.replace(tplContent, "#{el-form-item}#", elContent);
        tplContent = StrUtil.replace(tplContent, "//data_init","{"+ String.join(",\r\n", ms)+"}");
        tplContent = StrUtil.replace(tplContent, "//import_file", String.join("\r\n", importFiles));
        FileUtil.writeString(tplContent, listPath, Charset.defaultCharset());
        this.reWriteConfigJs("add");
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


