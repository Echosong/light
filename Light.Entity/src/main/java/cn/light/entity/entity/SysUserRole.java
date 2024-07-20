package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002719:40</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "用户角色", dto = false, controller = false, viewFrom = false, viewList = false, service = false)
public class SysUserRole extends SysBase{
    @AutoEntityField("角色id")
    private Integer roleId;


    @AutoEntityField("用户id")
    private Integer userId;
}
