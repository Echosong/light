package cn.light.entity.repository;
import java.util.*;
import java.math.BigDecimal;

import cn.light.common.dto.permission.PermissionQueryDTO;
import cn.light.entity.entity.KdPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: 自动生成 权限</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 19:08:09</p >
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


    @Query(nativeQuery = true, value = "select  * from kd_permission")
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