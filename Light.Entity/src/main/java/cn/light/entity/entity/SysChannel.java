package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.AutoSorted;
import cn.light.common.anno.InQueryDTO;
import cn.light.packet.enums.ChannelGradeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
//@AutoCover(CodeTypeEnum.LIST_VIEW)
public class SysChannel extends SysBase{
    @AutoEntityField(value = "渠道名")
    @InQueryDTO
    @AutoSorted
    private String channelName;

    //公司
    @AutoEntityField(value = "保险公司")
    @InQueryDTO
    @AutoSorted
    private String companyName;

    @AutoEntityField(value = "渠道等级", enums = ChannelGradeEnum.class)
    @AutoSorted
    private Integer grade;

    //方案
    @AutoEntityField(value = "渠道方案")
    private String scheme;


    //伤残比例
    @AutoEntityField(value = "伤残比例")
    @AutoSorted
    private String accidentRate;

    //成本价
    @AutoEntityField(value = "成本价")
    @AutoSorted
    private String costPrice;

}
