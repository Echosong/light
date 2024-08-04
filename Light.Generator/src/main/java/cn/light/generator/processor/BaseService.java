package cn.light.generator.processor;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.light.common.anno.AutoEntity;
import cn.light.generator.Const;


import java.net.URLDecoder;
import java.nio.charset.Charset;
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
            if(!StrUtil.endWith(Const.VUE_PATH, "system")) {
                Const.VUE_PATH = Const.VUE_PATH + "/system";
            }
        }else {
            //类目
            this.className = StrUtil.subAfter(this.tableName, Const.TABLE_PREFIX, false);
            if(!StrUtil.endWith(Const.VUE_PATH, Const.TABLE_PREFIX)) {
                Const.VUE_PATH = Const.VUE_PATH + "/" + Const.TABLE_PREFIX;
            }
        }
    }

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
    protected String  templatePath = "templates/auto/";

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
            String path =  URLDecoder.decode(paths.get(0),"utf-8");
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
    protected String replaceTpl(String tplName){
        String tplContent =  ResourceUtil.readStr(tplName, Charset.defaultCharset());
        //替换表说明
        tplContent = tplContent.replace("#{tableInfo}#", tableInfo);
        tplContent = tplContent.replace("#{SYS_PATH}", Const.SYS_PATH);
        tplContent = tplContent.replace("#{EntityName}#", StrUtil.lowerFirst(className));
        tplContent = tplContent.replace("#{UpEntityName}#", className);
        tplContent = tplContent.replace("#{UpTableName}#", StrUtil.upperFirst(tableName));
        tplContent = tplContent.replace("#{TableName}#", StrUtil.lowerFirst(tableName));
        tplContent = tplContent.replace("#{PackageName}#", packageName);
        tplContent = tplContent.replace("#{table_name}#", StrUtil.toUnderlineCase(tableName).toLowerCase());
        tplContent = StrUtil.replace(tplContent,"#{import}#",
                String.join("\r\n", this.importPackage));
        tplContent = StrUtil.replace(tplContent, "#{localDate}#", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

        if(StrUtil.isBlank(keyName)) {
            String subBefore = StrUtil.subBefore(tplContent, "//start", true);
            String subAfter = StrUtil.subAfter(tplContent, "//end", true);
            tplContent = subBefore + subAfter;
        }else {
            tplContent = StrUtil.replace(tplContent, "#{keyName}#", keyName);
            tplContent = StrUtil.replace(tplContent, "//start", "");
            tplContent = StrUtil.replace(tplContent, "//end", "");
        }

        return tplContent;
    }


}
