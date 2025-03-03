package cn.light.generator.processor;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import cn.light.common.anno.AutoEntityField;
import cn.light.entity.entity.SysBase;
import cn.light.generator.config.Const;
import cn.light.generator.dto.DictionaryDto;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


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

    private static final List<DictionaryDto> DICTIONARY_LIST = new ArrayList<>();


    @Override
    public void start() {
        DictionaryDto dto = new DictionaryDto();
        dto.setTableName(tableName);
        dto.setTableInfo(tableInfo);

        Field[] fields = clazz.getDeclaredFields();

        Field[] declaredFields = SysBase.class.getDeclaredFields();
        fields = ArrayUtil.addAll(declaredFields,fields);

        List<DictionaryDto.TableData> tableDataList = new ArrayList<>();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(AutoEntityField.class)) {
                continue;
            }
            DictionaryDto.TableData tableData = getTableData(field);

            tableDataList.add(tableData);

        }
        dto.setItems(tableDataList);
        DICTIONARY_LIST.add(dto);
    }

    private static DictionaryDto.TableData getTableData(Field field) {
        AutoEntityField annotation = field.getAnnotation(AutoEntityField.class);
        String name = field.getName();
        String columnName = annotation.value();
        int len = annotation.len();
        String isNull = "Y";
        String typeString = field.getType().getSimpleName();
        if (field.isAnnotationPresent(NotNull.class) || field.isAnnotationPresent(Id.class)) {
            isNull = "N";
        }
        DictionaryDto.TableData tableData = new DictionaryDto.TableData();
        tableData.setFieldName(name);
        tableData.setFieldType(typeString);
        tableData.setFieldLen(String.valueOf(len));
        tableData.setFieldNull(isNull);
        tableData.setFieldInfo(columnName);
        return tableData;
    }

    /**
     * 写入字典
     */
    public static void writeDictionaryFile() {
        String fileName = Const.ROOT_PATH+"/doc/data.html";
        if (FileUtil.isFile(fileName)) {
            FileUtil.del(fileName);
        }

        TemplateConfig templateConfig = new TemplateConfig(Const.TEMPLATE_PATH, TemplateConfig.ResourceMode.CLASSPATH);
        templateConfig.setCharset(StandardCharsets.UTF_8);
        TemplateEngine engine = TemplateUtil.createEngine(templateConfig);

        var template = engine.getTemplate("data.tpl");
        Dict dict = Dict.create().set("dictionaryList", DICTIONARY_LIST);

        var tplContent = template.render(dict);
        tplContent = StrUtil.replace(tplContent, "dateTime", DateUtil.format(new DateTime(), "yyyy-MM-dd HH:mm:ss"));
        FileUtil.writeString(tplContent, fileName, Charset.defaultCharset());
    }
}
