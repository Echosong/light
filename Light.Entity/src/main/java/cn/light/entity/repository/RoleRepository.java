package cn.light.entity.repository;
import java.util.*;

import cn.light.entity.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: 自动生成 所属客户</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 19:08:09</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Repository
@SuppressWarnings("ALL")
public interface RoleRepository extends JpaRepository<SysRole, Integer> {
    /**
     * 根据 多个id 获取角色
     * @param ids
     * @return
     */
    List<SysRole> getAllByIdIn(List<Integer> ids);
}