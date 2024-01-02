package cn.light.server.service.impl;


import cn.light.entity.entity.KdRole;
import cn.light.entity.entity.KdUserRole;
import cn.light.entity.repository.RoleRepository;
import cn.light.entity.repository.UserRoleRepository;
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
    public List<KdRole> listByUser(Integer userId) {
        List<KdUserRole> allByUserId = userRoleRepositroy.findAllByUserId(userId);
        List<Integer> roleIds = allByUserId.stream().map(KdUserRole::getRoleId).collect(Collectors.toList());
        return roleRepository.getAllByIdIn(roleIds);
    }
}
