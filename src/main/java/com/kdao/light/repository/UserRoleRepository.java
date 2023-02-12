package com.kdao.light.repository;

import com.kdao.light.entity.KdUserRole;
import com.kdao.light.entity.KdUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/28 002814:28</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Repository
public interface UserRoleRepository extends JpaRepository<KdUserRole, Integer> {
    /**
     * 根据用户获取角色信息
     *
     * @param userId
     * @return
     */
    List<KdUserRole> findAllByUserId(Integer userId);

    /**
     * 多个用户id查询记录
     * @param userIds
     * @return
     */
    List<KdUserRole> findAllByUserIdIn(Set<Integer> userIds);

    /**
     * 根据角色查询是否用户绑定
     * @param roleId
     * @return
     */
    List<KdUserRole> findAllByRoleId(Integer roleId);
}
