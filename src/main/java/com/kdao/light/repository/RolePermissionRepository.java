package com.kdao.light.repository;

import com.kdao.light.entity.KdRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/28 002814:57</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Repository
@SuppressWarnings("ALL")
public interface RolePermissionRepository extends JpaRepository<KdRolePermission, Integer> {
    /**
     * 根据角色获取当前权限信息
     * @param roleIds
     * @return
     */
    List<KdRolePermission> getAllByRoleIdIn(List<Integer> roleIds);
}
