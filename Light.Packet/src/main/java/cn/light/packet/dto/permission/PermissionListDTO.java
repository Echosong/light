package cn.light.packet.dto.permission;

import cn.light.common.anno.AutoEntityField;
import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.PageInfo;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.*;


/**
 * <p>Title:权限 </p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 19:23:19</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionListDTO extends SysBaseDTO implements IDictionaryObject {
    @NotBlank(message = "权限名称不能为空")
    @Schema(title = "权限名称")
    private String name;

    @Schema(title = "权限描述")
    private String description;

    @Schema(title = "访问路径")
    private String url;

    @NotBlank(message = "权限标识不能为空")
    @Schema(title = "权限标识")
    private String perms;

    //路径
    @Schema(title = "路径")
    private String path;

    @Range(min = 0)
    @Schema(title = "父级id")
    private Integer parentId;

    @Range(min = 0, max = 3)
    @Schema(title = "类型", description = "权限类型 0：目录   1：菜单   2：按钮")
    private Integer type;

    @Schema(title = "排序")
    private Integer sort;

    @Schema(title = "图标")
    private String icon;

    @Schema(title = "是否展现 1 展现 0 不展现")
    private Integer show;

    @Schema(title = "组件")
    private String component;


}
