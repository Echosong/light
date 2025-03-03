package cn.light.packet.dto.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

/**
 * RolePermissDTO
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 * @date : 2024/7/28 18:35
 */
@Getter
@Setter
public class RolePermissionDTO {
    @Schema(description = "权限集")
    private List<PermissionListDTO> menuTreeList;

    @Schema(description = "选择的id")
    private Collection<Integer> selectedMenuId;
}
