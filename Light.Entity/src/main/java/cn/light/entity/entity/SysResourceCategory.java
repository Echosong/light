package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.enums.HtmlTypeEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 资源分类
 * @author : 二胡子
 * @version :1.0.0
 */
@AutoEntity(value = "资源分类")
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
public class SysResourceCategory extends SysBase {
    @AutoEntityField(value = "标题", isKeyName = true)
    @InQueryDTO
    private String title;

    @AutoEntityField(value = "排序")
    private Integer sort;

}
