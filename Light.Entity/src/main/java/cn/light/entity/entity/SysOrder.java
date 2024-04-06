package cn.light.entity.entity;

import cn.light.common.anno.AutoCover;
import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.enums.CodeTypeEnum;
import cn.light.common.enums.HtmlTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
    private Date orderTime;

    //业绩归属
    @AutoEntityField(value = "业绩归属")
    @InQueryDTO
    private Date owner;

    //客户名称
    @AutoEntityField(value = "客户名称")
    @InQueryDTO
    private String customerName;

    @AutoEntityField(value = "渠道名称")
    @InQueryDTO
    private String channelName;

    //保险公司
    @AutoEntityField(value = "保险公司")
    private String companyName;

    //方案
    @AutoEntityField(value = "方案")
    private String scheme;

    //渠道等级
    @AutoEntityField(value = "渠道等级")
    private String grade;

    //伤残比例
    @AutoEntityField(value = "伤残比例")
    private String accidentRate;

    //三类人数
    @AutoEntityField(value = "三类人数")
    private Integer threeClass;

    //三类售价
    @AutoEntityField(value = "三类售价")
    private String threeClassPrice;

    //三类渠道价
    @AutoEntityField(value = "三类底价")
    private String threeClassChannelPrice;

    //四类人数
    @AutoEntityField(value = "四类人数")
    private Integer fourClass;

    //四类渠道价
    @AutoEntityField(value = "四类底价")
    private String fourClassChannelPrice;

    //四类售价
    @AutoEntityField(value = "四类售价")
    private String fourClassPrice;

    //五类人数
    @AutoEntityField(value = "五类人数")
    private Integer fiveClass;

    //五类渠道价
    @AutoEntityField(value = "五类底价")
    private String fiveClassChannelPrice;

    //五类售价
    @AutoEntityField(value = "五类售价")
    private String fiveClassPrice;

    //运营
    @AutoEntityField(value = "运营人", htmlType = HtmlTypeEnum.SELECT, source = "User")
    @InQueryDTO
    private String operation;

    //返利总额
    @AutoEntityField(value = "返利总额")
    private String totalRebate;

    //利润
    @AutoEntityField(value = "利润")
    private String profit;

    //回本日期
    @AutoEntityField(value = "回本日期")
    private Date backTime;

}
