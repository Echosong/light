package cn.light.packet.dto.ad;

import cn.light.packet.enums.system.*;
import cn.light.packet.enums.business.*;
import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import java.util.*;



/**
 * Ad列表传输对象
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-12-01 16:24:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AdListDTO extends SysBaseDTO implements IDictionaryObject {
    @Schema(title = "位置", description = "AdPositionEnum")
    @ApiModelPropertyEnum(AdPositionEnum.class)
    private Integer position;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "关联代码")
    private String relationCode;

    @Schema(title = "图片")
    private String image;

    @Schema(title = "跳转位置")
    private String url;

    @Schema(title = "状态", description = "YesOrNoEnum")
    @ApiModelPropertyEnum(YesOrNoEnum.class)
    private Integer state;

    @Schema(title = "关联的实体")
    private String info;


}
