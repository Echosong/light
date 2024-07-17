package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.AutoEntityFieldDefault;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.enums.HtmlTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 业绩数据
 * @author : 二胡子
 * @version :1.0.0
 */
@AutoEntity(value = "业绩数据")
@Getter
@Setter
@Entity
@Table(indexes = {
        @Index(name = "idx_order_name", columnList = "customer_name")
})
@DynamicInsert
@DynamicUpdate
//@AutoCover(value = {CodeTypeEnum.LIST_VIEW, CodeTypeEnum.QUERY_DTO, CodeTypeEnum.MAPPER_XML, CodeTypeEnum.ADD_VIEW})
public class SysOrder extends  SysBase{
    @AutoEntityField(value = "订单日期")
    @NotNull
    private Date orderTime;

    //业绩归属
    @AutoEntityField(value = "业绩归属")
    @InQueryDTO
    @NotNull
    private Date owner;

    //客户名称
    @AutoEntityField(value = "客户名称")
    @InQueryDTO
    @NotNull
    private String customerName;

    @AutoEntityField(value = "渠道名称")
    @InQueryDTO
    @NotNull
    private String channelName;

    //保险公司
    @AutoEntityField(value = "保险公司")
    @NotNull
    private String companyName;

    //方案
    @AutoEntityField(value = "方案")
    @NotNull
    private String scheme;

    //渠道等级
    @AutoEntityField(value = "渠道等级")
    private String grade;

    //伤残比例
    @AutoEntityField(value = "伤残比例")
    private String accidentRate;

    //三类人数
    @AutoEntityField(value = "三类人数")
    @AutoEntityFieldDefault("0")
    private Integer threeClass;

    //三类售价
    @AutoEntityField(value = "三类售价")
    @AutoEntityFieldDefault("0")
    private BigDecimal threeClassPrice;

    //三类渠道价
    @AutoEntityField(value = "三类渠道价")
    @AutoEntityFieldDefault("0")
    private BigDecimal threeClassChannelPrice;

    //四类人数
    @AutoEntityField(value = "四类人数")
    @AutoEntityFieldDefault("0")
    private Integer fourClass;

    //四类渠道价
    @AutoEntityField(value = "四类渠道价")
    @AutoEntityFieldDefault("0")
    private BigDecimal fourClassChannelPrice;

    //四类售价
    @AutoEntityField(value = "四类售价")
    @AutoEntityFieldDefault("0")
    private BigDecimal fourClassPrice;

    //五类人数
    @AutoEntityField(value = "五类人数")
    @AutoEntityFieldDefault("0")
    private Integer fiveClass;

    //五类渠道价
    @AutoEntityField(value = "五类渠道价")
    @AutoEntityFieldDefault("0")
    private BigDecimal fiveClassChannelPrice;

    //五类售价
    @AutoEntityField(value = "五类售价")
    @AutoEntityFieldDefault("0")
    private BigDecimal fiveClassPrice;

    //运营
    @AutoEntityField(value = "运营人", htmlType = HtmlTypeEnum.SELECT, source = "User")
    @InQueryDTO
    private Integer operation;

    //返利总额
    @AutoEntityField(value = "返利总额")
    private BigDecimal totalRebate;

    //利润
    @AutoEntityField(value = "利润")
    private BigDecimal profit;

    @AutoEntityField(value = "渠道id")
    private Integer channelId;

    //回本日期
    @AutoEntityField(value = "回本日期")
    private Date backTime;

}