package cn.light.packet.dto.areaDj;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.PageInfo;
import cn.light.common.dto.SysBaseDTO;
import cn.light.packet.enums.system.*;
import cn.light.packet.enums.business.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.*;

/**
 * 行政区划实体 pojo转化
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AreaDjDTO extends SysBaseDTO implements IDictionaryObject {
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
