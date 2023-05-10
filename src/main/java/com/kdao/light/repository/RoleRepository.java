package com.kdao.light.repository;

import com.kdao.light.entity.KdRole;
import com.kdao.light.entity.KdRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002719:49</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Repository
@SuppressWarnings("ALL")
public interface RoleRepository extends JpaRepository<KdRole, Integer> {
    /**
     * 根据 多个id 获取角色
     * @param ids
     * @return
     */
    List<KdRole> getAllByIdIn(List<Integer> ids);

}
