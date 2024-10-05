package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.AutoEntityFieldDefault;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.enums.HtmlTypeEnum;
import cn.light.packet.enums.system.AdPositionEnum;
import cn.light.packet.enums.system.YesOrNoEnum;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 广告管理
 * @author hz
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "广告管理")
public class SysAd extends SysBase{

    @AutoEntityField(value = "位置", enums = AdPositionEnum.class)
    @NotNull
    @InQueryDTO
    private  Integer position;

    @AutoEntityField(value = "名称")
    @NotNull
    @InQueryDTO
    private String name;

    @AutoEntityField(value = "关联代码")
    private String relationCode;

    @AutoEntityField(value = "图片", htmlType = HtmlTypeEnum.UPLOAD)
    private String image;

    @AutoEntityField(value = "跳转位置")
    private String url;

    @AutoEntityField(value = "状态", enums = YesOrNoEnum.class)
    @AutoEntityFieldDefault(value = "10")
    private Integer state;

    @AutoEntityField(value = "关联的实体", htmlType = HtmlTypeEnum.TEXTAREA)
    private String info;

}
