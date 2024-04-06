package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.enums.HtmlTypeEnum;
import cn.light.packet.enums.DicTypeStateEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 字典数据表 sys_dict_data
 * @author : 二胡子
 * @version :1.0.0
 */
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "字典数据")
public class SysDictData extends SysBase{

    /** 字典编码 */
    @AutoEntityField(value = "字典编码")
    private Long dictCode;

    /** 字典排序 */
    @AutoEntityField(value = "字典排序")
    private Long dictSort;

    /** 字典标签 */
    @AutoEntityField(value = "字典标签")
    private String dictLabel;

    /** 字典键值 */
    @AutoEntityField(value = "字典键值", htmlType = HtmlTypeEnum.TEXTAREA)
    private String dictValue;

    /** 字典类型 */
    @AutoEntityField(value = "字典类型")
    private String dictType;

    /** 样式属性（其他样式扩展） */
    @AutoEntityField(value = "样式属性")
    private String cssClass;

    /** 表格字典样式 */
    @AutoEntityField(value = "表格字典样式")
    private String listClass;

    /** 是否默认（Y是 N否） */
    @AutoEntityField(value = "是否默认", notes = "Y=是,N=否")
    private Integer defaultFlg;

    /** 状态（1正常 0停用） */
    @AutoEntityField(value = "状态", notes = "1=正常,0=停用", enums = DicTypeStateEnum.class)
    private Integer status;
}
