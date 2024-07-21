package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.packet.enums.DicTypeStateEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *字典类型表 sys_dict_type
 * @author : 二胡子
 * @version :1.0.0
 */
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "字典类型")
public class SysDictType extends SysBase{
    /** 字典名称 */
    @AutoEntityField(value = "字典名称")
    private String dictName;

    /** 字典编码 */
    @AutoEntityField(value = "字典编码")
    private String dictCode;

    /** 字典类型 */
    @AutoEntityField(value = "字典类型")
    private String dictType;

    //分组
    @AutoEntityField(value = "分组")
    private String groupName;

    /** 状态（1正常 0停用） */
    @AutoEntityField(value = "状态", notes = "1=正常,0=停用", enums = DicTypeStateEnum.class)
    private Integer state;
}
