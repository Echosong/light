package cn.light.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Execl导出所有用到的列信息
 * @author : 二胡子
 * @version :1.0.0
 */
@Data
public class ExtendColumnInfo {
    private static final int ORIGIN_COLUMN_COUNT = 3;
    private static final String COLUMN_INFO_SPLIT = ",";
    @Schema(defaultValue = "列名")
    private String columnName;
    @Schema(
            defaultValue = "列类型",
            allowableValues = "VARCHAR,INT,FLOAT,DECIMAL,DATE,DATETIME"
    )
    private String columnType;
    @Schema(defaultValue = "列所用控件名")
    private String frontControlName;
    @Schema(defaultValue = "英文显示名")
    private String enName;
    @Schema(defaultValue = "中文显示名")
    private String chName;
}
