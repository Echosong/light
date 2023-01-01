package com.kdao.light.common.dto.permission;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0223:55</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Data
public class PermissionDTO implements Serializable {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("权限名称")
    @NotBlank(message = "权限名称不能为空")
    private String name;

    @ApiModelProperty("权限描述")
    private String description;

    @ApiModelProperty("访问路径")
    private String url;

    @ApiModelProperty("权限标识")
    @NotBlank(message = "权限标识不能为空")
    private String perms;

    @ApiModelProperty("父级id")
    @Range(min = 0)
    private Integer parentId;

    @ApiModelProperty(value = "权限类型 0：目录   1：菜单   2：按钮",allowableValues = "0,1,2")
    @Range(min = 0, max = 3)
    private Integer type;

    @ApiModelProperty("排序")
    private Integer sort = 0;

    @ApiModelProperty("图标")
    private String icon;
}
