package cn.light.server.service.impl;


import cn.hutool.core.util.StrUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.PageUtil;
import cn.light.entity.entity.SysPermission;
import cn.light.entity.entity.SysRolePermission;
import cn.light.entity.entity.SysUserRole;
import cn.light.entity.mapper.PermissionMapper;
import cn.light.entity.repository.PermissionRepository;
import cn.light.entity.repository.RolePermissionRepository;
import cn.light.entity.repository.UserRoleRepository;
import cn.light.packet.dto.permission.PermissionDTO;
import cn.light.packet.dto.permission.PermissionListDTO;
import cn.light.packet.dto.permission.PermissionQueryDTO;
import cn.light.packet.dto.permission.RolePermissionDTO;
import cn.light.server.service.PermissionService;
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
    private  RolePermissionRepository rolePermissionRepository;
    @Resource
    private  PermissionRepository permissionRepository;
    @Resource
    private UserRoleRepository userRoleRepositroy;


    @Override
    public List<SysPermission> getListByrole(List<Integer> roleIds) {

        List<SysRolePermission> allByRoleIdIn = rolePermissionRepository.getAllByRoleIdIn(roleIds);
        Set<Integer> permissionIds = allByRoleIdIn.stream().map(SysRolePermission::getPermissionId).collect(Collectors.toSet());
        return  permissionRepository.getAllByIdIn(new ArrayList<>(permissionIds))
                .stream()
                .sorted(Comparator.comparing(SysPermission::getSort))
                .collect(Collectors.toList());
    }

    @Override
    public List<PermissionDTO> listByUser(Integer userId) {
        List<SysUserRole> userRoles = userRoleRepositroy.findAllByUserId(userId);
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

        return permissions;
    }

    @Override
    public void updateRolePermissions(List<Integer> permissionIds, Integer roleId) {
        List<SysRolePermission> allByRoleIdIn = rolePermissionRepository.getAllByRoleIdIn(Collections.singletonList(roleId));
        if(!allByRoleIdIn.isEmpty()){
            rolePermissionRepository.deleteAll(allByRoleIdIn);
        }
        List<SysRolePermission> kdRolePermissions = new ArrayList<>();
        for (Integer permissionId : permissionIds) {
            SysRolePermission kdRolePermission = new SysRolePermission();
            kdRolePermission.setPermissionId(permissionId);
            kdRolePermission.setRoleId(roleId);
            kdRolePermissions.add(kdRolePermission);
        }
        rolePermissionRepository.saveAll(kdRolePermissions);
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
            permissionRepository.findById(kdPermission.getParentId())
                    .orElseThrow(() -> new BaseKnownException(600, "上级权限不存在"));
        }
        permissionRepository.save(kdPermission);
    }

    @Override
    public RolePermissionDTO getRoleSelectedMenu(Integer roleId){
        List<SysRolePermission> rolePermissions = rolePermissionRepository.getAllByRoleIdIn(List.of(roleId));
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
        var permissions = permissionRepository.findByParentId(parentId)
                .stream()
                .sorted(Comparator.comparing(SysPermission::getSort))
                .toList();
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
