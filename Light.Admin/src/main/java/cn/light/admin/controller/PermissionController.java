package cn.light.admin.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import cn.light.common.annotation.NoPermission;
import cn.light.common.annotation.Permission;
import cn.light.packet.dto.permission.PermissionDTO;
import cn.light.packet.dto.permission.PermissionQueryDTO;
import cn.light.common.enums.LogicalEnum;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.PageUtil;
import cn.light.entity.entity.KdPermission;
import cn.light.entity.entity.KdRolePermission;
import cn.light.entity.entity.KdUserRole;
import cn.light.entity.mapper.PermissionMapper;
import cn.light.entity.repository.PermissionRepository;
import cn.light.entity.repository.RolePermissionRepository;
import cn.light.entity.repository.UserRoleRepository;
import cn.light.server.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
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
 * <p>Title: </p >
 * <p>Description: 权限管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 20:15:44</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "权限 控制器")
@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseController{
    @Resource
    private  PermissionRepository permissionRepository;
    @Resource
    private  UserRoleRepository userRoleRepositroy;
    @Resource
    private  RolePermissionRepository rolePermissionRepository;
    @Resource
    private PermissionService permissionService;

    @Resource
    private  PermissionMapper permissionMapper;



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