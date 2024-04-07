package cn.light.packet.dto.order;

import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
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

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>Title: 业绩数据</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-04-04 10:49:56</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderDTO extends SysBaseDTO implements IDictionaryObject {

    @Schema(title = "业绩归属")
    private Date owner;

    @Schema(title = "订单日期")
    private Date orderTime;

    @Schema(title = "客户名称")
    private String customerName;

    @Schema(title = "渠道名称")
    private String channelName;

    @Schema(title = "保险公司")
    private String companyName;

    @Schema(title = "方案")
    private String scheme;

    @Schema(title = "渠道等级")
    private String grade;

    @Schema(title = "伤残比例")
    private String accidentRate;

    @Schema(title = "三类人数")
    private Integer threeClass;

    @Schema(title = "三类售价")
    private BigDecimal threeClassPrice;

    @Schema(title = "三类渠道价")
    private BigDecimal threeClassChannelPrice;

    @Schema(title = "四类人数")
    private Integer fourClass;

    @Schema(title = "四类渠道价")
    private BigDecimal fourClassChannelPrice;

    @Schema(title = "四类售价")
    private BigDecimal fourClassPrice;

    @Schema(title = "五类人数")
    private Integer fiveClass;

    @Schema(title = "五类渠道价")
    private BigDecimal fiveClassChannelPrice;

    @Schema(title = "五类售价")
    private BigDecimal fiveClassPrice;

    @Schema(title = "运营人")
    private Integer operation;

    @Schema(title = "返利总额")
    private BigDecimal totalRebate;

    @Schema(title = "利润")
    private BigDecimal profit;

    @Schema(title = "回本日期")
    private Date backTime;

    @Schema(title = "渠道id")
    private Integer channelId;


}
