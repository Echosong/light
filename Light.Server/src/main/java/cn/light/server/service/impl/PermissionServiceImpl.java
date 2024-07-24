package cn.light.server.service.impl;


import cn.light.common.util.DtoMapper;
import cn.light.entity.entity.SysPermission;
import cn.light.entity.entity.SysRolePermission;
import cn.light.entity.entity.SysUserRole;
import cn.light.entity.mapper.PermissionMapper;
import cn.light.entity.repository.PermissionRepository;
import cn.light.entity.repository.RolePermissionRepository;
import cn.light.entity.repository.UserRoleRepository;
import cn.light.packet.dto.permission.PermissionDTO;
import cn.light.server.service.PermissionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
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

    /**
     * 根据角色获取角色下所有权限
     * @param roleIds
     * @return
     */
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

        return DtoMapper.convertList(listByrole , PermissionDTO.class);
    }
}
