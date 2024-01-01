package com.kdao.light.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import com.kdao.light.common.annotation.NoPermission;
import com.kdao.light.common.annotation.Permission;
import com.kdao.light.common.dto.permission.PermissionDTO;
import com.kdao.light.common.dto.permission.PermissionQueryDTO;
import com.kdao.light.common.enums.LogicalEnum;
import com.kdao.light.common.exception.BaseKnownException;
import com.kdao.light.common.utils.DtoMapper;
import com.kdao.light.common.utils.PageUtil;
import com.kdao.light.entity.KdPermission;
import com.kdao.light.entity.KdRolePermission;
import com.kdao.light.entity.KdUserRole;
import com.kdao.light.mapper.PermissionMapper;
import com.kdao.light.repository.PermissionRepository;
import com.kdao.light.repository.RolePermissionRepository;
import com.kdao.light.repository.UserRoleRepository;
import com.kdao.light.service.PermissionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限菜单
 * @author : echosong
 * @version :1.0.0
 */
@RestController
@Tag(name = "权限菜单")
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    private final PermissionRepository permissionRepository;
    private final UserRoleRepository userRoleRepositroy;
    private final RolePermissionRepository rolePermissionRepository;
    private final PermissionService permissionService;

    @Resource
    private  PermissionMapper permissionMapper;

    @Autowired
    public PermissionController(PermissionRepository permissionRepository, UserRoleRepository userRoleRepositroy,
                                RolePermissionRepository rolePermissionRepository, PermissionService permissionService) {
        this.permissionRepository = permissionRepository;
        this.userRoleRepositroy = userRoleRepositroy;
        this.rolePermissionRepository = rolePermissionRepository;
        this.permissionService = permissionService;
    }

    /**
     * 新建或者更新权限信息
     * @param kdPermission
     */
    @PostMapping("/save")
    @Operation(summary = "新建|更新权限信息")
    @CacheEvict(value = "Permission_all", allEntries = true)
    @Permission(roles = "admin", logical = LogicalEnum.AND)
    public void save(@RequestBody @Valid KdPermission kdPermission){
        if(kdPermission.getParentId() != 0) {
            permissionRepository.findById(kdPermission.getParentId())
                    .orElseThrow(() -> new BaseKnownException(600, "上级权限不存在"));
        }
        permissionRepository.save(kdPermission);
    }

    /**
     * 分页相关查询
     * @param permissionQueryDTO
     * @return
     */
    @PutMapping("/listPage")
    @Operation(summary = "查询列表")
    @NoPermission
    public Page<KdPermission> list(@RequestBody PermissionQueryDTO permissionQueryDTO){
        return PageUtil.getPage(permissionMapper::listPage, permissionQueryDTO);
    }

    /**
     * 获取所有权限
     * @return
     */
   // @Cacheable(value = "Permission_all") 开发阶段先去掉缓存
    @GetMapping("/all")
    @Operation(summary = "获取所有权限")
    public List<PermissionDTO> all(){
        return DtoMapper.convertList( permissionRepository.findAll().stream()
                        .sorted(Comparator.comparing(KdPermission::getSort))
                        .collect(Collectors.toList()),
                PermissionDTO.class);
    }

    /**
     * 获取当前用户的权限列表
     * @return
     */
    @GetMapping("/listByUser")
    @Operation(summary = "根据用户获取当前用户具有的菜单权限")
    public List<PermissionDTO> listByUser(){
        Integer userId = Convert.toInt(StpUtil.getLoginId(), 0);
        List<KdUserRole> userRoles = userRoleRepositroy.findAllByUserId(userId);
        List<PermissionDTO> kdPermissions = new ArrayList<>();
        if(userRoles.isEmpty()){
            return kdPermissions;
        }
        List<Integer> roleIds = userRoles.stream().map(KdUserRole::getRoleId)
                        .collect(Collectors.toList());
        List<KdPermission> listByrole = permissionService.getListByrole(roleIds);

        return DtoMapper.convertList(listByrole , PermissionDTO.class);
    }

    /**
     * 根据角色id获取权限
     * @param roleId
     * @return
     */
    @GetMapping("/listByRole/{roleId}")
    @Operation(summary = "根据角色id获取权限")
    public List<KdPermission> listByRole(@NotBlank @PathVariable Integer roleId){
        return  permissionService.getListByrole(Arrays.asList(roleId));
    }

    /**
     * 更新某个角色的权限信息
     * @param permissionIds
     * @param roleId
     */
    @PutMapping("/updateRolePermissions/{roleId}")
    @Operation(summary = "更新某个角色的权限信息")
    public void updateRolePermissions(@RequestBody List<Integer> permissionIds, @PathVariable Integer roleId){
        List<KdRolePermission> allByRoleIdIn = rolePermissionRepository.getAllByRoleIdIn(Arrays.asList(roleId));
        if(!allByRoleIdIn.isEmpty()){
            rolePermissionRepository.deleteAll(allByRoleIdIn);
        }
        List<KdRolePermission> kdRolePermissions = new ArrayList<>();
        for (Integer permissionId : permissionIds) {
            KdRolePermission kdRolePermission = new KdRolePermission();
            kdRolePermission.setPermissionId(permissionId);
            kdRolePermission.setRoleId(roleId);
            kdRolePermissions.add(kdRolePermission);
        }
        rolePermissionRepository.saveAll(kdRolePermissions);
    }

}
