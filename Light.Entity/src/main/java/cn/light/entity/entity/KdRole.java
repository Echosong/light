package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


/**
 * <p>Title: </p >
 * <p>Description: 角色| 部门</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002719:39</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "所属客户")
public class KdRole extends SysBase  {
    @NotNull
    @AutoEntityField(value = "客户名称")
    @InQueryDTO
    private String name;

    @AutoEntityField(value = "描述")
    private String description;


}
