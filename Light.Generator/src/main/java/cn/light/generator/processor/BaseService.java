package cn.light.generator.processor;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import cn.light.common.anno.AutoEntity;
import cn.light.generator.config.Const;


import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0413:03</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
public class BaseService {

    protected Class<?> clazz;

    protected AutoEntity autoEntity;

    BaseService(Class<?> clazz){
        this.clazz = clazz;
        if(clazz.isAnnotationPresent(AutoEntity.class)){
            this.autoEntity = clazz.getAnnotation(AutoEntity.class);
            this.tableInfo = autoEntity.value();
        }

        //表名称
        this.tableName = clazz.getSimpleName();

        if(StrUtil.startWith(this.tableName,"Sys")){
            this.className = StrUtil.subAfter(this.tableName,"Sys", false);
            Const.VUE_PATH = Const.VUE_PATH_TEMP + "/system";
        }else {
            //类目
            this.className = StrUtil.subAfter(this.tableName, Const.TABLE_PREFIX, false);
            Const.VUE_PATH = Const.VUE_PATH_TEMP + "/business" ;
        }
    }


    /**
     * 模板对象
     */
    protected Template template;

    /**
     * 表名
     */
    protected String tableName;
    /**
     * 类名
     */
    protected String className;
    /**
     * 表说明
     */
    protected String tableInfo = "";
    /**
     * 包名
     */
    protected String packageName = "";

    /**
     * 导入信息
     */
    protected Set<String> importPackage = new HashSet<>();

    /**
     * 模板文件
     */
    protected String  templatePath = Const.TEMPLATE_PATH;

    /**
     * 是否需要简单查询
     */
    protected String keyName = "";

    /**
     * 获取当前绝对路径
     *
     * @return 获取正式路径
     */
    protected String getRealPath(String packageName) {
        List<String> paths = new ArrayList<>(ClassUtil.getClassPaths(packageName));
        try {
            String path =  URLDecoder.decode(paths.get(0), StandardCharsets.UTF_8);
            path = StrUtil.replaceIgnoreCase(path, "target/classes", "src/main/java");
            path = StrUtil.replaceIgnoreCase(path, "target/test-classes", "src/main/java");
            return  StrUtil.trim(path);
        }catch (Exception ignored){
            return "";
        }
    }

    /**
     * 魔板读取替换基础信息
     * @param tplName 模板路径名
     * @return 初步处理后模板内容
     */
    protected Dict replaceTpl(String tplName){
        template = SingleEngine.getInstance().getTemplate(tplName);
        Dict dict = Dict.create();
        dict.set("tableInfo", tableInfo);
        dict.set("SYS_PATH", Const.SYS_PATH);
        dict.set("EntityName", StrUtil.lowerFirst(className));
        dict.set("UpEntityName", StrUtil.upperFirst(className));
        dict.set("UpTableName", StrUtil.upperFirst(tableName));
        dict.set("TableName", StrUtil.lowerFirst(tableName));
        dict.set("table_name",StrUtil.toUnderlineCase(tableName).toLowerCase());
        dict.set("PackageName", packageName);
        dict.set("tableName",StrUtil.toUnderlineCase(tableName).toLowerCase());
        dict.set("import",String.join("\r\n", this.importPackage));
        dict.set("localDate", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        dict.set("keyName",keyName);
        dict.set("isKey",StrUtil.isNotBlank(keyName));
        return dict;
    }


}
