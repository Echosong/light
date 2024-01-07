package cn.light.entity.repository;
import java.util.*;

import cn.light.entity.entity.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: 自动生成 用户角色</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 19:08:09</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Repository
@SuppressWarnings("ALL")
public interface UserRoleRepository extends JpaRepository<SysUserRole, Integer> {
    /**
     * 根据用户获取角色信息
     *
     * @param userId
     * @return
     */
    List<SysUserRole> findAllByUserId(Integer userId);

    /**
     * 多个用户id查询记录
     * @param userIds
     * @return
     */
    List<SysUserRole> findAllByUserIdIn(Set<Integer> userIds);

    /**
     * 根据角色查询是否用户绑定
     * @param roleId
     * @return
     */
    List<SysUserRole> findAllByRoleId(Integer roleId);
}