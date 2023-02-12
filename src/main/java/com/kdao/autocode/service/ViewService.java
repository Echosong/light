package com.kdao.autocode.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.kdao.autocode.Const;
import com.kdao.autocode.anno.*;
import com.kdao.autocode.enums.CodeTypeEnum;
import com.kdao.light.common.dto.PageInfo;
import com.kdao.light.common.enums.BaseEnum;
import com.kdao.light.common.enums.HtmlTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    private static final String  INPUT_ENUM = "<input-enum";

    /**
     * 写入配置文件
     *
     * @param typeStr 类型字符
     */
    private void reWriteConfigJs(String typeStr) {
        Map<String, String> map = new HashMap<>(12);
        map.put("name", StrUtil.lowerFirst(this.className));
        map.put("type", typeStr);
        String perms = StrUtil.format("{perms: \"{name}-{type}\", view: () => import('@/sa-view/{name}/{type}.vue')},",
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
        String templateFile = this.templatePath + "list.tpl";
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
        //追加一个id编号
        tableColumns.add("<el-table-column type=\"selection\"></el-table-column>");
        List<String> mapFile = new ArrayList<>();

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
            }
            if (autoEntityField.htmlType() == HtmlTypeEnum.UPLOAD) {
                mapFile.add(StrUtil.format(" item.{}File = JSON.parse(item.{});", field.getName(), field.getName()));
            }
        }
        PageInfo pageInfo = new PageInfo();
        String queryParam = StrUtil.format("pageSize:{},page:{}", pageInfo.getPageSize(), pageInfo.getPage());
        if (paramsList.isEmpty()) {
            queryParam = "{" + queryParam + "}";
        } else {
            queryParam = "{" + queryParam + ", " + String.join(",", paramsList) + "}";
        }

        String elContent = String.join("\r\n", fromStr);
        tplContent = StrUtil.replace(tplContent, "#{el-form-item}#", elContent);
        //处理枚举下来组件
        if (elContent.contains("<input-enum")) {
            tplContent = StrUtil.replaceIgnoreCase(tplContent, "//import inputEnum from \"../../sa-resources/com-view/input-enum.vue\";",
                    "import inputEnum from \"../../sa-resources/com-view/input-enum.vue\";");
            tplContent = StrUtil.replaceIgnoreCase(tplContent, "//inputEnum,", "inputEnum,");
        }

        tplContent = StrUtil.replace(tplContent, "#{queryPageParams}#", queryParam);
        tplContent = StrUtil.replace(tplContent, "#{el-table-column}#",
                String.join("\r\n", tableColumns));
        tplContent = StrUtil.replace(tplContent, "//map_file", String.join("\r\n", mapFile));

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
            String uploadListTemplate = "" +
                    " <el-table-column  label=\"{}\"  >\n" +
                    "    <template slot-scope=\"s\">\n" +
                    "        <el-link  v-for=\"item in (s.row.{}File)\"  :key=\"item.name\" :href=\"item.url\"  type=\"primary\" >{{item.name}}</el-link>\n" +
                    "     </template>\n" +
                    " </el-table-column> ";
            returnValue = StrUtil.format(uploadListTemplate, autoEntityField.value(), typeName);
        } else {
            returnValue = StrUtil.format("" +
                            "                   <el-table-column  label=\"{}\"   prop=\"{}\" ></el-table-column>",
                    autoEntityField.value(), typeName);
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
            returnValue = StrUtil.format("<el-form-item label=\"{}：\">\n" +
                    " <el-input v-model=\"p.{}\" placeholder=\"模糊查询\"></el-input>\n" +
                    "</el-form-item>", autoEntityField.value(), field.getName());
        }
        if ("Date".equals(field.getType().getSimpleName())) {
            returnValue = StrUtil.format("<el-form-item label=\"{}：\">\n" +
                            "          <el-date-picker\n" +
                            "            v-model=\"p.start{}\"\n" +
                            "            type=\"datetime\"\n" +
                            "            value-format=\"yyyy-MM-dd HH:mm:ss\"\n" +
                            "            placeholder=\"开始日期\"\n" +
                            "          ></el-date-picker>\n" +
                            "          -\n" +
                            "          <el-date-picker\n" +
                            "            v-model=\"p.end{}\"\n" +
                            "            type=\"datetime\"\n" +
                            "            value-format=\"yyyy-MM-dd HH:mm:ss\"\n" +
                            "            placeholder=\"结束日期\"\n" +
                            "          ></el-date-picker>\n" +
                            "        </el-form-item>", autoEntityField.value(), StrUtil.upperFirst(field.getName()),
                    StrUtil.upperFirst(field.getName()));
        }

        if (!BASE_ENUM.equals(autoEntityField.enums().getSimpleName())) {
            Class<? extends BaseEnum> lclazz = autoEntityField.enums();

            returnValue = StrUtil.format("<el-form-item label=\"{}\">\n" +
                            "                    <input-enum\n" +
                            "                        enumName=\"{}\"\n" +
                            "                        v-model=\"p.{}\"\n" +
                            "                    ></input-enum>\n" +
                            "                </el-form-item>",
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
        if( !annotation.value().equals(codeTypeEnum)){
            return true;
        }
        //进行备份
        FileUtil.copy(listPath, listPath.replace(".vue"
                        , StrUtil.format("_{}.txt", DateUtil.format(LocalDateTime.now(),"yyyMMddHHmmss")))
                ,true);
        return false;
    }

    private String getFromUploadFileStr(int type) {
        String cardStr = type == 0?"":" list-type=\"picture-card\" \n";
        String fromUploadFileStr = "<el-upload\n" +
                "  class=\"upload-demo\"\n" +
                "  :action=\"sa.cfg.api_url + '/file/upload'\"\n" +
                "  :multiple=\"false\"\n" +
                "  :data=\"{ fileType: 2, params: '' }\"" +
                "  :limit=\"10\"\n" +
                " {$cardStr$}"+
                "   :on-success=\"success_{name}\"" +
                "   :before-remove=\"remove_{name}\"" +
                "  :file-list=\"m.{name}File\">\n" +
                "  <el-button size=\"mini\" type=\"primary\">点击上传</el-button>\n" +
                "  <div slot=\"tip\" class=\"el-upload__tip\">上传{info}</div>\n" +
                "</el-upload>";
        return fromUploadFileStr.replace("{$cardStr$}", cardStr);
    }

    /**
     * 编辑
     */
    @SuppressWarnings("AlibabaMethodTooLong")
    private void viewFrom() {
        String templateFile = this.templatePath + "add.tpl";

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
        List<String> uploadCallback = new ArrayList<>();
        List<String> replaceFiles = new ArrayList<>();
        List<String> replaceOlds = new ArrayList<>();
        List<String> rulesFields = new ArrayList<>();
        List<String> textEdits = new LinkedList<>();

        for (Field field : declaredFields) {
            if (!field.isAnnotationPresent(AutoEntityField.class)) {
                continue;
            }
            AutoEntityField annotation = field.getAnnotation(AutoEntityField.class);
            rulesFields.add(this.validationRules(field));
            //处理上传得各种回调函数
            if (annotation.htmlType() == HtmlTypeEnum.UPLOAD) {
                Map<String, String> upMap = new HashMap<>(1);
                upMap.put("name", field.getName());
                String successUploadFun = "" +
                        "success_{name}(response, file, fileList) {\n" +
                        "          if(response.code != 200){\n" +
                        "            this.sa.error(response.message);\n" +
                        "            return;\n" +
                        "          }\n" +
                        "          if(!this.m.{name}File){\n" +
                        "            this.m.{name}File = [];\n" +
                        "          }" +
                        "          this.m.{name}File.push(response.data);\n" +
                        "          console.log(fileList);\n" +
                        "        },";
                uploadCallback.add(StrUtil.format(successUploadFun, upMap));
                String removeUploadFun = " " +
                        "remove_{name}(file, fileList){\n" +
                        "    this.m.{name}File = fileList;\n" +
                        "},";
                uploadCallback.add(StrUtil.format(removeUploadFun, upMap));
                String replaceOlderFile = "" +
                        "       this.m.{name} =JSON.stringify(this.m.{name}File.map(item=>{\n" +
                        "              let a = {};\n" +
                        "              a.name = item.name;\n" +
                        "              a.url = item.url;\n" +
                        "              return a;" +
                        "       }));";
                replaceOlds.add(StrUtil.format(replaceOlderFile, upMap));
                replaceFiles.add(StrUtil.format("data.{name}File = JSON.parse(data.{name});", upMap));
            }
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
                        //暂时一个页面只考虑一个上传
                        Map<String, String> uploadMap = new HashMap<>(2);
                        uploadMap.put("name", field.getName());
                        uploadMap.put("info", annotation.value());
                        htmlTypeStr = StrUtil.format(getFromUploadFileStr(0), uploadMap);
                        ms.add(StrUtil.format("{}File:[]", field.getName()));
                        break;
                    case FILE:
                        //暂时一个页面只考虑一个上传
                        Map<String, String> fileMap = new HashMap<>(2);
                        fileMap.put("name", field.getName());
                        fileMap.put("info", annotation.value());
                        htmlTypeStr = StrUtil.format(getFromUploadFileStr(1),fileMap);
                        ms.add(StrUtil.format("{}File:[]", field.getName()));
                        break;
                    case TEXTEDIT:
                        htmlTypeStr = StrUtil.format( " <div class=\"editor-box\"> <div id = \"{}\" ref= \"{}\" style = \"text-align:left\" >" +
                            "</div></div> ", field.getName(), field.getName());
                        textEdits.add(StrUtil.format("this.create_editor(\"{}\");", field.getName()));
                        break;
                    default:
                }
                elFormItems.add(StrUtil.format("<el-form-item label=\"{}\">\n" +
                        "{}</el-form-item>", annotation.value(), htmlTypeStr));

                continue;
            }

            if (!"BaseEnum".equals(annotation.enums().getSimpleName())) {
                Class<? extends BaseEnum> lclazz = annotation.enums();
                elFormItems.add(StrUtil.format("<el-form-item label=\"{}\">\n" +
                                "                    <input-enum\n" +
                                "                        enumName=\"{}\"\n" +
                                "                        v-model=\"m.{}\"\n" +
                                "                    ></input-enum>\n" +
                                "                </el-form-item>", annotation.value(), StrUtil.lowerFirst(lclazz.getSimpleName()),
                        field.getName()));
                continue;
            }

            if ("Date".equals(field.getType().getSimpleName())) {
                elFormItems.add(StrUtil.format("<el-form-item label=\"{}：\"  prop=\"{}\" >\n" +
                        " <el-date-picker\n" +
                        "            v-model=\"m.{}\"\n" +
                        "            type=\"datetime\"\n" +
                        "            value-format=\"yyyy-MM-dd HH:mm:ss\"\n" +
                        "            placeholder=\"{}\"\n" +
                        "          ></el-date-picker>" +
                        " </el-form-item>", annotation.value(), field.getName(), field.getName(), annotation.value()));
            } else {
                elFormItems.add(StrUtil.format("<el-form-item label=\"{}：\"  prop=\"{}\" >\n" +
                        "            <el-input v-model=\"m.{}\"></el-input>\n" +
                        "          </el-form-item>", annotation.value(), field.getName(), field.getName()));
            }
        }
        //
        String elContent = String.join("\r\n", elFormItems);
        if (elContent.contains(INPUT_ENUM)) {
            tplContent = StrUtil.replaceIgnoreCase(tplContent, "//import inputEnum from \"../../sa-resources/com-view/input-enum.vue\";",
                    "import inputEnum from \"../../sa-resources/com-view/input-enum.vue\";");
            tplContent = StrUtil.replaceIgnoreCase(tplContent, "//components: { inputEnum },", "components: { inputEnum },");
        }
        if(textEdits.size() > 0){
            tplContent = StrUtil.replaceIgnoreCase(tplContent, "//import E from \"wangeditor\";", "import E from \"wangeditor\";");
            tplContent =  StrUtil.replaceIgnoreCase(tplContent,"/*create_editor", "").replace("create_editor*/", "");
            tplContent =  StrUtil.replaceIgnoreCase(tplContent,"//create_editor", String.join("\r\n", textEdits));
            tplContent =  StrUtil.replaceIgnoreCase(tplContent,"width=\"550px\"", "width=\"850px\"");
        }

        tplContent = StrUtil.replace(tplContent, "//rule_fields", String.join("\r\n", rulesFields));
        tplContent = StrUtil.replace(tplContent, "#{el-form-item}#", elContent);
        tplContent = StrUtil.replace(tplContent, "#{data_init}#", String.join(",\r\n", ms));
        tplContent = StrUtil.replace(tplContent, "//upload_functions", String.join("\r\n", uploadCallback));
        tplContent = StrUtil.replace(tplContent, "//replace_file", String.join("\r\n", replaceFiles));
        tplContent = StrUtil.replace(tplContent, "//replace_old", String.join("\r\n", replaceOlds));
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
