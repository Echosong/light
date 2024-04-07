package cn.light.entity.entity;

import cn.light.common.anno.*;
import cn.light.common.enums.CodeTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * 渠道信息表
 * @author : 二胡子
 * @version :1.0.0
 */
@AutoEntity(value = "渠道信息表")
@Getter
@Setter
@Entity
@Table(indexes = {
        @Index(name = "idx_channel_name", columnList = "channel_name")
})
@DynamicInsert
@DynamicUpdate
public class SysChannel extends SysBase{
    @AutoEntityField(value = "渠道名")
    @InQueryDTO
    @AutoSorted
    @NotNull
    private String channelName;

    //公司
    @AutoEntityField(value = "保险公司")
    @InQueryDTO
    @AutoSorted
    @NotNull
    private String companyName;

    //方案
    @AutoEntityField(value = "渠道方案")
    @NotNull
    private String scheme;


    //伤残比例
    @AutoEntityField(value = "伤残比例")
    @AutoSorted
    @NotNull
    private String accidentRate;

    //三类成本价
    @AutoEntityField(value = "三类成本价")
    @Size(min = 0)
    @NotNull
    private BigDecimal threeClassCostPrice;

    //四类成本价
    @AutoEntityField(value = "四类成本价")
    @Size(min = 0)
    @NotNull
    private BigDecimal fourClassCostPrice;

    //五类成本价
    @AutoEntityField(value = "五类成本价")
    @Size(min = 0)
    @NotNull
    private BigDecimal fiveClassCostPrice;

    //三类渠道价
    @AutoEntityField(value = "三类渠道价")
    @Size(min = 0)
    @NotNull
    private BigDecimal threeClassChannelPrice;

    //四类渠道价
    @AutoEntityField(value = "四类渠道价")
    @Size(min = 0)
    @NotNull
    private BigDecimal fourClassChannelPrice;

    //五类渠道价
    @AutoEntityField(value = "五类渠道价")
    @Size(min = 0)
    @NotNull
    private BigDecimal fiveClassChannelPrice;

}
