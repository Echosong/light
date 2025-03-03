package cn.light.packet.dto.ad;

import cn.light.packet.enums.system.*;
import cn.light.packet.enums.business.*;
import cn.light.common.dto.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.light.common.annotation.ApiModelPropertyEnum;
import lombok.Data;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import java.util.*;

/**
* AdQueryDTO 查询DTO
* email:zq_songfeigang@163.com
*
* @author : 二胡子
* @version : 1.0
* @date : 2024-12-01 16:24:23
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class AdQueryDTO extends PageInfo {
    @Schema(title = "位置", description = "AdPositionEnum")
    @ApiModelPropertyEnum(AdPositionEnum.class)
    private Integer position;

    @Schema(title = "名称")
    private String name;


}
