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



/**
 * <p>Title:渠道信息表 </p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-04-06 23:32:03</p >
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

   @Schema(title="渠道方案" )
    private String scheme;

   @Schema(title="伤残比例" )
    private String accidentRate;

   @Schema(title="三类成本价" )
    private BigDecimal threeClassCostPrice;

   @Schema(title="四类成本价" )
    private BigDecimal fourClassCostPrice;

   @Schema(title="五类成本价" )
    private BigDecimal fiveClassCostPrice;

   @Schema(title="三类渠道价" )
    private BigDecimal threeClassChannelPrice;

   @Schema(title="四类渠道价" )
    private BigDecimal fourClassChannelPrice;

   @Schema(title="五类渠道价" )
    private BigDecimal fiveClassChannelPrice;


}
