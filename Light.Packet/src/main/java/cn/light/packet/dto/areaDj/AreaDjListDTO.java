package cn.light.packet.dto.areaDj;

import cn.light.packet.enums.system.*;
import cn.light.packet.enums.business.*;
import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.PageInfo;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.*;



/**
 * AreaDj列表传输对象
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-10-05 21:10:39
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AreaDjListDTO extends SysBaseDTO implements IDictionaryObject {
    @Schema(title = "父级code")
    private String parentCode;

    @Schema(title = "省市区县名")
    private String areaName;

    @Schema(title = "省市区县")
    private String areaCode;

    @Schema(title = "状态", description = "AdStateEnum")
    @ApiModelPropertyEnum(AdStateEnum.class)
    private Integer status;

    @Schema(title = "是否修改", description = "YesOrNoEnum")
    @ApiModelPropertyEnum(YesOrNoEnum.class)
    private Integer isAdd;

    @Schema(title = "省市区", description = "AreaTypeEnum")
    @ApiModelPropertyEnum(AreaTypeEnum.class)
    private Integer type;

    @Schema(title = "拼音")
    private String pinyin;


}
