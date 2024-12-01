package cn.light.entity.entity;

import cn.light.common.anno.AutoEntityField;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @Description: 专门用来生产单据号
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
public class SysCode extends SysBase{

    //表名
    @AutoEntityField(value = "表名")
    private String name;

    @AutoEntityField(value = "单号")
    private Long number;

    @AutoEntityField(value = "哈希值")
    private String hashCode;
}
