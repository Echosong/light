package cn.light.server.service.impl;


import cn.light.entity.entity.SysRole;
import cn.light.entity.entity.SysUserRole;
import cn.light.entity.repository.RoleRepository;
import cn.light.entity.repository.UserRoleRepository;
import cn.light.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/28 002816:39</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final UserRoleRepository userRoleRepositroy;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(UserRoleRepository userRoleRepositroy, RoleRepository roleRepository) {
        this.userRoleRepositroy = userRoleRepositroy;
        this.roleRepository = roleRepository;
    }

    /**
     * 根据用户获取当前角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> listByUser(Integer userId) {
        List<SysUserRole> allByUserId = userRoleRepositroy.findAllByUserId(userId);
        List<Integer> roleIds = allByUserId.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        return roleRepository.getAllByIdIn(roleIds);
    }
}
