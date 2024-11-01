package cn.light.packet.dto.ad;

import cn.light.packet.enums.system.*;
import cn.light.packet.enums.business.*;
import cn.light.common.dto.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.light.common.annotation.ApiModelPropertyEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.*;

/**
* AdQueryDTO 查询DTO
* email:zq_songfeigang@163.com
*
* @author : 二胡子
* @version : 1.0
* @date : 2024-10-05 21:07:18
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
