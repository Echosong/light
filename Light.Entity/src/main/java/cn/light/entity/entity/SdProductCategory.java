package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.AutoEntityFieldDefault;
import cn.light.common.enums.HtmlTypeEnum;
import cn.light.packet.enums.AdStateEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 商品分类
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "商品分类")
public class SdProductCategory extends SysBase{
    //上级id
    @AutoEntityField(value = "上级id")
    private Integer parentId;

    //分类名称
    @AutoEntityField(value = "分类名称", isKeyName = true)
    private String categoryName;

    //分类排序
    @AutoEntityField(value = "分类排序")
    private Integer sort;

    //分类图片
    @AutoEntityField(value = "分类图片", htmlType = HtmlTypeEnum.UPLOAD)
    private String img;

    //商品数量
    @AutoEntityField(value = "商品数量")
    @AutoEntityFieldDefault(value = "0")
    private Integer count;

    //状态
    @AutoEntityField(value = "状态", enums = AdStateEnum.class)
    @AutoEntityFieldDefault(value = "10")
    private Integer state;

}
