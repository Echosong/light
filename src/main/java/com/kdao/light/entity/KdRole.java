package com.kdao.light.entity;

import com.kdao.autocode.anno.AutoEntity;
import com.kdao.light.common.component.IDictionaryObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

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
public class KdRole extends SysBase implements IDictionaryObject {
    @NotNull
    @ApiModelProperty("客户名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;


}
