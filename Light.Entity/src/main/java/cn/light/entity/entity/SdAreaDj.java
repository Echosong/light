package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.packet.enums.business.AdStateEnum;
import cn.light.packet.enums.business.AreaTypeEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 行政区划实体
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "行政区划实体")
//@AutoCover(value = {CodeTypeEnum.CONTROLLER})
public class SdAreaDj extends  SysBase{
    @AutoEntityField(value = "父级code")
    private String parentCode;

    @AutoEntityField(value = "省市区县名",isKeyName = true)
    private String areaName;

    @AutoEntityField(value = "省市区县")
    private String areaCode;

    @AutoEntityField(value = "状态", enums = AdStateEnum.class)
    private Integer status;

    @AutoEntityField(value = "是否修改0否1是")
    private Integer isAdd;

    @AutoEntityField(value = "省市区", enums = AreaTypeEnum.class)
    private Integer type;

    @AutoEntityField(value = "拼音")
    private String pinyin;
}
