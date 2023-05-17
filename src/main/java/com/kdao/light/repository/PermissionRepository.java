package com.kdao.light.repository;

import com.github.lkqm.spring.jpa.repository.MybatisQuery;
import com.kdao.light.common.dto.permission.PermissionQueryDTO;
import com.kdao.light.entity.KdPermission;
import com.kdao.light.entity.KdPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002719:50</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Repository
@SuppressWarnings("ALL")
public interface PermissionRepository extends JpaRepository<KdPermission, Integer> {
    /**
     * 根据id集合查询相关权限列表
     * @param ids
     * @return
     */
    List<KdPermission> getAllByIdIn(List<Integer> ids);


    @MybatisQuery
    List<KdPermission> getListPage(PermissionQueryDTO permissionQueryDTO);

    /**
     * 分页获取权限信息
     * @param perms
     * @param name
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "select  * from kd_permission")
    Page<KdPermission> getAllByPermsAndNameContainingAndType(String perms, String name, Integer type, Pageable pageable);
}
