package cn.light.server.service.impl;


import cn.light.entity.entity.KdPermission;
import cn.light.entity.entity.KdRolePermission;
import cn.light.entity.repository.PermissionRepository;
import cn.light.entity.repository.RolePermissionRepository;
import cn.light.server.service.PermissionService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private  RolePermissionRepository rolePermissionRepository;
    @Resource
    private  PermissionRepository permissionRepository;



    /**
     * 根据角色获取角色下所有权限
     * @param roleIds
     * @return
     */
    @Override
    public List<KdPermission> getListByrole(List<Integer> roleIds) {
        List<KdRolePermission> allByRoleIdIn = rolePermissionRepository.getAllByRoleIdIn(roleIds);
        Set<Integer> permissionIds = allByRoleIdIn.stream().map(KdRolePermission::getPermissionId).collect(Collectors.toSet());
        return  permissionRepository.getAllByIdIn(new ArrayList<>(permissionIds))
                .stream()
                .sorted(Comparator.comparing(KdPermission::getSort))
                .collect(Collectors.toList());
    }
}
