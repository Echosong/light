package cn.light.entity.entity;

import cn.light.common.anno.AutoCover;
import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.enums.CodeTypeEnum;
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
@AutoCover(value = {CodeTypeEnum.LIST_VIEW, CodeTypeEnum.MAPPER_XML, CodeTypeEnum.QUERY_DTO})
public class SysChannel extends SysBase{
    @AutoEntityField(value = "渠道名")
    @InQueryDTO
    private String channelName;

    //公司
    @AutoEntityField(value = "保险公司")
    @InQueryDTO
    private String companyName;

    @AutoEntityField(value = "渠道等级")
    private String grade;

    //方案
    @AutoEntityField(value = "渠道方案")
    private String scheme;


    //伤残比例
    @AutoEntityField(value = "伤残比例")
    private String accidentRate;

    //成本价
    @AutoEntityField(value = "成本价")
    private String costPrice;

}
