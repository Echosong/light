package cn.light.admin.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import cn.light.common.annotation.NoPermission;
import cn.light.common.annotation.Permission;
import cn.light.common.enums.LogicalEnum;
import cn.light.common.util.DtoMapper;
import cn.light.entity.entity.SysPermission;
import cn.light.packet.dto.permission.PermissionDTO;
import cn.light.packet.dto.permission.PermissionListDTO;
import cn.light.packet.dto.permission.PermissionQueryDTO;
import cn.light.packet.dto.permission.RolePermissionDTO;
import cn.light.server.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: 权限管理
 * listPage,save,delete,find,list
 *
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "权限 控制器")
@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    @Resource
    private PermissionService permissionService;

    @PostMapping("/save")
    @Operation(summary = "新建|更新权限信息")
    @CacheEvict(value = "Permission_all", allEntries = true)
    @Permission(roles = "admin", logical = LogicalEnum.AND)
    public void save(@RequestBody @Valid PermissionDTO permissionDTO) {
        permissionService.save(permissionDTO);
    }


    @PutMapping("/listPage")
    @Operation(summary = "查询列表")
    @NoPermission
    public Page<PermissionListDTO> list(@RequestBody PermissionQueryDTO permissionQueryDTO) {
        return permissionService.listPage(permissionQueryDTO);
    }


    @GetMapping("/all")
    @Operation(summary = "获取所有权限")
    public List<PermissionDTO> all() {
        return DtoMapper.convertList(permissionService.list().stream()
                        .sorted(Comparator.comparing(SysPermission::getSort))
                        .collect(Collectors.toList()),
                PermissionDTO.class);
    }


    @GetMapping("/listByUser")
    @Operation(summary = "根据用户获取当前用户具有的菜单权限")
    public List<PermissionDTO> listByUser() {
        Integer userId = Convert.toInt(StpUtil.getLoginId(), 0);
        return permissionService.listByUser(userId);
    }


    @Operation(summary = "根据角色id获取权限")
    @GetMapping("/getRoleSelectedMenu/{roleId}")
    public RolePermissionDTO getRoleSelectedMenu(@PathVariable Integer roleId){
        return permissionService.getRoleSelectedMenu(roleId);
    }


    @PutMapping("/updateRolePermissions/{roleId}")
    @Operation(summary = "更新某个角色的权限信息")
    public void updateRolePermissions(@RequestBody List<Integer> permissionIds, @PathVariable(value = "roleId") Integer roleId) {
        permissionService.updateRolePermissions(permissionIds, roleId);
    }

}