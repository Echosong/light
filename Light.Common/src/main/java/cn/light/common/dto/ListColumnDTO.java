package cn.light.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 * @date : 2024/8/3 18:51
 */
@Getter
@Setter
public class ListColumnDTO {


    @Schema(defaultValue = "列标题")
    private String title;

    @Schema(defaultValue = "列名")
    private String dataIndex;

    @Schema(defaultValue = "是否显示省略号,默认不显示")
    private Boolean ellipsis = true ;

    @Schema(defaultValue = "列对齐方式,默认居中 left | right | center")
    private String align = "left";

    @Schema(defaultValue = "列宽度")
    private Object width;

    @Schema(defaultValue = "表头显示下一次排序的 tooltip 提示")
    private String showSorterTooltip;

    @Schema(defaultValue = "列固定位置,默认不固定 left | right")
    private String fixed;

    @Schema(defaultValue = "排序字段")
    private Boolean sorter = false;

}
