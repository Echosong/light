package cn.light.server.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.PageUtil;
import cn.light.entity.cache.UserPermissionCache;
import cn.light.entity.cache.UserPermissionCacheRepository;
import cn.light.entity.entity.SysPermission;
import cn.light.entity.entity.SysRolePermission;
import cn.light.entity.entity.SysUserRole;
import cn.light.entity.mapper.PermissionMapper;
import cn.light.entity.mapper.RolePermissionMapper;
import cn.light.entity.mapper.UserRoleMapper;
import cn.light.packet.dto.permission.PermissionDTO;
import cn.light.packet.dto.permission.PermissionListDTO;
import cn.light.packet.dto.permission.PermissionQueryDTO;
import cn.light.packet.dto.permission.RolePermissionDTO;
import cn.light.server.service.PermissionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/28 002815:38</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, SysPermission> implements PermissionService  {
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private UserPermissionCacheRepository userPermissionCacheRepository;


    @Override
    public List<SysPermission> getListByrole(List<Integer> roleIds) {

        List<SysRolePermission> allByRoleIdIn = rolePermissionMapper.getAllByRoleIdIn(roleIds);
        Set<Integer> permissionIds = allByRoleIdIn.stream().map(SysRolePermission::getPermissionId).collect(Collectors.toSet());
        return  this.baseMapper.selectBatchIds(permissionIds)
                .stream()
                .sorted(Comparator.comparing(SysPermission::getSort))
                .collect(Collectors.toList());
    }

    @Override
    public List<PermissionDTO> listByUser(Integer userId) {
        Optional<UserPermissionCache> userPermissionCacheOptional = userPermissionCacheRepository.findById(userId);
        if (userPermissionCacheOptional.isPresent()){
            return userPermissionCacheOptional.get().getPermissions();
        }

        List<SysUserRole> userRoles = userRoleMapper.findAllByUserId(userId);
        List<PermissionDTO> kdPermissions = new ArrayList<>();
        if(userRoles.isEmpty()){
            return kdPermissions;
        }
        List<Integer> roleIds = userRoles.stream().map(SysUserRole::getRoleId)
                .collect(Collectors.toList());
        List<SysPermission> listByrole = this.getListByrole(roleIds);

        List<PermissionDTO> permissions = DtoMapper.convertList(listByrole, PermissionDTO.class);
        for (PermissionDTO permission : permissions) {
            if(StrUtil.isBlank(permission.getPerms())){
                continue;
            }
            if(StrUtil.isBlank(permission.getComponent())){
                permission.setComponent("/"+permission.getPerms().replaceAll("/", "-"));
            }
            permission.setPath(permission.getPerms());
        }
        userPermissionCacheRepository.save(new UserPermissionCache(userId, permissions));

        return permissions;
    }

    @Override
    public void updateRolePermissions(List<Integer> permissionIds, Integer roleId) {
        rolePermissionMapper.delete(new LambdaQueryWrapper<SysRolePermission>().eq(SysRolePermission::getRoleId, roleId));

        for (Integer permissionId : permissionIds) {
            SysRolePermission kdRolePermission = new SysRolePermission();
            kdRolePermission.setPermissionId(permissionId);
            kdRolePermission.setRoleId(roleId);
            rolePermissionMapper.insert(kdRolePermission);
        }

        //清理角色下所有用户缓存
        List<SysUserRole> allByRoleId = userRoleMapper.findAllByRoleId(roleId);
        for (SysUserRole sysUserRole : allByRoleId) {
            userPermissionCacheRepository.deleteById(sysUserRole.getUserId());
        }
    }

    @Override
    public Page<PermissionListDTO> listPage(PermissionQueryDTO permissionQueryDTO) {
        Page<SysPermission> dataPages  =  PageUtil.getPage(this.baseMapper::listPage, permissionQueryDTO);
        Page<PermissionListDTO> pageList = DtoMapper.convertPage(dataPages, PermissionListDTO.class);
        for (PermissionListDTO permission : pageList.getContent()) {
            if(StrUtil.isBlank(permission.getPerms())){
                continue;
            }
            if(StrUtil.isBlank(permission.getComponent())){
                permission.setComponent("/"+permission.getPerms().replaceAll("/", "-"));
            }
            permission.setPath(permission.getPerms());
        }
        return pageList;
    }

    @Override
    public void save(PermissionDTO permissionDTO) {
        SysPermission kdPermission = DtoMapper.convert(permissionDTO, SysPermission.class);
        if(kdPermission.getParentId() != 0) {
            this.getOptById(kdPermission.getParentId())
                    .orElseThrow(() -> new BaseKnownException(600, "上级权限不存在"));
        }
        this.save(kdPermission);
    }

    @Override
    public RolePermissionDTO getRoleSelectedMenu(Integer roleId){
        List<SysRolePermission> rolePermissions = rolePermissionMapper.getAllByRoleIdIn(List.of(roleId));
        Set<Integer> permissionIds = rolePermissions.stream().map(SysRolePermission::getPermissionId)
                .collect(Collectors.toSet());
        RolePermissionDTO rolePermissionDTO = new RolePermissionDTO();
        rolePermissionDTO.setMenuTreeList(getTreePermissions(0));
        rolePermissionDTO.setSelectedMenuId(permissionIds);
        return rolePermissionDTO;
    }


    @Override
    public List<PermissionListDTO> getTreePermissions(Integer parentId) {
        List<PermissionListDTO> permissionList = new ArrayList<>();
        var permissions = this.lambdaQuery().eq(SysPermission::getParentId, parentId)
                .orderByAsc(SysPermission::getSort)
                .list();

        for (SysPermission permission : permissions) {
            if (permission.getParentId().equals(parentId)) {
                PermissionListDTO permissionDTO = DtoMapper.convert(permission, PermissionListDTO.class);
                permissionDTO.setChildren(getTreePermissions(permission.getId()));
                permissionList.add(permissionDTO);
            }
        }
        return permissionList;
    }
}
