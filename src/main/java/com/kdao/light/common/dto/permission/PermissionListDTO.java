package com.kdao.light.common.dto.permission;

import com.kdao.light.common.component.IDictionaryObject;
import com.kdao.light.common.dto.SysBaseDTO;
import io.swagger.annotations.ApiModelProperty;
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
    @NotBlank(message="权限名称不能为空")
    @ApiModelProperty(value="权限名称", notes="")
    private String name;

    @ApiModelProperty(value="权限描述", notes="")
    private String description;

    @ApiModelProperty(value="访问路径", notes="")
    private String url;

    @NotBlank(message="权限标识不能为空")
    @ApiModelProperty(value="权限标识", notes="")
    private String perms;

    @Range(min=0)
    @ApiModelProperty(value="父级id", notes="")
    private Integer parentId;

    @Range(min=0,max=3)
    @ApiModelProperty(value="类型", notes="权限类型 0：目录   1：菜单   2：按钮")
    private Integer type;

    @ApiModelProperty(value="排序", notes="")
    private Integer sort;

    @ApiModelProperty(value="图标", notes="")
    private String icon;


}
