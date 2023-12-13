package com.kdao.light.common.dto.permission;

import com.kdao.light.common.component.IDictionaryObject;
import com.kdao.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * <p>Title:#{EntityTableName}# </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0319:48</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Data
public class PermissionListDTO extends SysBaseDTO implements IDictionaryObject {
    @NotBlank(message = "权限名称不能为空")
    @Schema(defaultValue = "权限名称")
    private String name;

    @Schema(defaultValue = "权限描述")
    private String description;

    @Schema(defaultValue = "访问路径")
    private String url;

    @NotBlank(message = "权限标识不能为空")
    @Schema(defaultValue = "权限标识")
    private String perms;

    @Range(min = 0)
    @Schema(defaultValue = "父级id")
    private Integer parentId;

    @Range(min = 0, max = 3)
    @Schema(defaultValue = "类型", description = "权限类型 0：目录   1：菜单   2：按钮")
    private Integer type;

    @Schema(defaultValue = "排序")
    private Integer sort;

    @Schema(defaultValue = "图标")
    private String icon;


}
