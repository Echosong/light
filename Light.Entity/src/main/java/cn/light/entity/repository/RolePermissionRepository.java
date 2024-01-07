package cn.light.entity.repository;
import java.util.*;

import cn.light.entity.entity.SysRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: 自动生成 </p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 19:08:09</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Repository
@SuppressWarnings("ALL")
public interface RolePermissionRepository extends JpaRepository<SysRolePermission, Integer> {
    /**
     * 根据角色获取当前权限信息
     * @param roleIds
     * @return
     */
    List<SysRolePermission> getAllByRoleIdIn(List<Integer> roleIds);
}