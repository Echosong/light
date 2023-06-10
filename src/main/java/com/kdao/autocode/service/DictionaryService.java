package com.kdao.autocode.service;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.kdao.autocode.Const;
import com.kdao.autocode.anno.AutoEntityField;
import com.kdao.light.entity.SysBase;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import javax.persistence.*;

/**
 * <p>Title: 自动生成数据字典</p >
 * <p>Description: DictionaryService</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2023-02-18 17:52 </p >
 *
 * @author : 二胡子
 * @version :1.0.0
 */
public class DictionaryService extends BaseService implements ServiceInterface {

    public DictionaryService(Class<?> clazz) {
        super(clazz);
    }

    /**
     * 用来存储全局模板
     */
    private static final StringBuilder DICTIONARY_STR = new StringBuilder();

    private static String tplContent = "";

    private static String path = "";

    @Override
    public void start() {

        Field[] fields = clazz.getDeclaredFields();
        DICTIONARY_STR.append("<div class=\"content\">\r\n");
        DICTIONARY_STR.append(StrUtil.format("<h2>{}({})</h2>", tableName, tableInfo));
        DICTIONARY_STR.append("<table><tr><th>名称</th><th>数据类型</th> <th>长度</th><th>允许空值</th><th>说明</th></tr>\r\n");
        Field[] declaredFields = SysBase.class.getDeclaredFields();
        fields = ArrayUtil.addAll(declaredFields,fields);

        for (Field field : fields) {
            if (!field.isAnnotationPresent(AutoEntityField.class)) {
                continue;
            }
            AutoEntityField annotation = field.getAnnotation(AutoEntityField.class);
            String name = field.getName();
            String columnName = annotation.value();
            int len = annotation.len();
            String isNull = "Y";
            String typeString = field.getType().getSimpleName();
            if (field.isAnnotationPresent(NotNull.class) || field.isAnnotationPresent(Id.class)) {
                isNull = "N";
            }
            DICTIONARY_STR.append("<tr>");
            DICTIONARY_STR.append(StrUtil.format("<td>{}</td>", name));
            DICTIONARY_STR.append(StrUtil.format("<td>{}</td>", typeString));
            DICTIONARY_STR.append(StrUtil.format("<td>{}</td>", len));
            DICTIONARY_STR.append(StrUtil.format("<td>{}</td>", isNull));
            DICTIONARY_STR.append(StrUtil.format("<td>{}</td>", columnName));
            DICTIONARY_STR.append("</tr>");
        }
        DICTIONARY_STR.append("</table></div>");
        if (StrUtil.isBlank(tplContent)) {
            String templateFile = this.templatePath + "data.tpl";
            tplContent = replaceTpl(templateFile);
            path = Const.ROOT_PATH+"/sql";
        }
    }

    /**
     * 写入字典
     */
    public static void writeDictionaryFile() {
        String fileName = path + "/data.html";
        if (FileUtil.isFile(fileName)) {
            FileUtil.del(fileName);
        }
        tplContent = StrUtil.replace(tplContent, "#{tableDiv}#", DICTIONARY_STR);
        tplContent = StrUtil.replace(tplContent, "{{dateTime}}", DateUtil.format(new DateTime(), "yyyy-MM-dd HH:mm:ss"));
        FileUtil.writeString(tplContent, fileName, Charset.defaultCharset());
    }
}
