package cn.light.generator.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DictionaryDto
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 * @date : 2024/11/30 22:43
 */
@Setter
@Getter
public class DictionaryDto {
    //表名称
    public String tableName;
    //表说明
    private String tableInfo;
    //字段信息
    private List<TableData> items;

    @Getter
    @Setter
    public static class TableData {
        //字段名称
        private String fieldName;
        //字段是否为主键
        private String fieldType;
        //字段说明
        private String fieldLen;
        //是否为null
        private String fieldNull;
        //字段说明
        private String fieldInfo;
     }
}
