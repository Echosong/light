package cn.light.entity.repository;
import java.util.*;

import cn.light.packet.dto.permission.PermissionQueryDTO;
import cn.light.entity.entity.SysPermission;
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
public interface PermissionRepository extends JpaRepository<SysPermission, Integer> {
    /**
     * 根据id集合查询相关权限列表
     * @param ids
     * @return
     */
    List<SysPermission> getAllByIdIn(List<Integer> ids);

    List<SysPermission> findByParentId(Integer parentId);




}