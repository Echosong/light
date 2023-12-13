package com.kdao.light.common.dto.permission;

import io.swagger.v3.oas.annotations.media.Schema;
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
   @Schema(defaultValue = "id")
    private Integer id;

   @Schema(defaultValue = "权限名称")
    @NotBlank(message = "权限名称不能为空")
    private String name;

   @Schema(defaultValue = "权限描述")
    private String description;

   @Schema(defaultValue = "访问路径")
    private String url;

   @Schema(defaultValue = "权限标识")
    @NotBlank(message = "权限标识不能为空")
    private String perms;

   @Schema(defaultValue = "父级id")
    @Range(min = 0)
    private Integer parentId;

   @Schema(defaultValue = "权限类型 0：目录   1：菜单   2：按钮",allowableValues = "0,1,2")
    @Range(min = 0, max = 3)
    private Integer type;

   @Schema(defaultValue = "排序")
    private Integer sort = 0;

   @Schema(defaultValue = "图标")
    private String icon;

   @Schema(defaultValue = "是否展现")
    private Integer show;
}
