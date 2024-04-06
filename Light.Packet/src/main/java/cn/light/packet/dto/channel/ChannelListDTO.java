package cn.light.packet.dto.channel;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.PageInfo;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.*;

import cn.light.packet.enums.ChannelGradeEnum;

/**
 * <p>Title:渠道信息表 </p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-04-06 13:16:47</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChannelListDTO extends SysBaseDTO implements IDictionaryObject {
   @Schema(title="渠道名" )
    private String channelName;

   @Schema(title="保险公司" )
    private String companyName;

   @Schema(title="渠道等级" )
    @ApiModelPropertyEnum(ChannelGradeEnum.class)
    private Integer grade;

   @Schema(title="渠道方案" )
    private String scheme;

   @Schema(title="伤残比例" )
    private String accidentRate;

   @Schema(title="成本价" )
    private String costPrice;


}
