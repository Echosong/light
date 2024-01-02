package cn.light.packet.dto.permission;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.packet.dto.PageInfo;
import cn.light.packet.dto.SysBaseDTO;
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
    @NotBlank(message="权限名称不能为空")
   @Schema(name="权限名称" )
    private String name;

   @Schema(name="权限描述" )
    private String description;

   @Schema(name="访问路径" )
    private String url;

    @NotBlank(message="权限标识不能为空")
   @Schema(name="权限标识" )
    private String perms;

    @Range(min=0)
   @Schema(name="父级id" )
    private Integer parentId;

    @Range(min=0,max=3)
   @Schema(name="类型" , description="权限类型 0：目录   1：菜单   2：按钮")
    private Integer type;

   @Schema(name="排序" )
    private Integer sort;

   @Schema(name="图标" )
    private String icon;

   @Schema(name="是否展现 1 展现 0 不展现" )
    private Integer show;


}
