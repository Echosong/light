package cn.light.entity.repository;
import java.util.*;
import java.math.BigDecimal;
import cn.light.entity.entity.KdUserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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