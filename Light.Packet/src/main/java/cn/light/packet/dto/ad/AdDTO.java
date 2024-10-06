package cn.light.packet.dto.ad;

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
 * 广告管理 pojo转化
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AdDTO extends SysBaseDTO implements IDictionaryObject {
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
