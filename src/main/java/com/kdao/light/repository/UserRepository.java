package com.kdao.light.repository;

import com.kdao.light.entity.KdUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * <p>Title: </p >
 * <p>Description: 用户表</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002719:28</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Repository
@SuppressWarnings("ALL")
public interface UserRepository extends JpaRepository<KdUser, Integer> {
    /**
     * 分页查询
     *
     * @param Username 账号
     * @param pageable 分页信息
     * @return
     */
    @Query(nativeQuery = true, value = "select * from kd_user where state != 3 and if(?1 !='', INSTR(username ,?1), true)" +
            " and  if(IFNULL(?2,1)=1,true , create_time >= ?2)" +
            " and  if(IFNULL(?3,1) =1,true, create_time <= ?3) ")
    Page<KdUser> findAllByUsernameContaining(String Username, LocalDate startDate, LocalDate endDate, Pageable pageable);


    /**
     * 根据账号查询用户
     *
     * @param username
     * @return
     */
    Optional<KdUser> findByUsername(String username);

    /**
     * 查询状态不为3 删除的用户
     *
     * @param state 用户信息
     * @return
     */
    List<KdUser> getAllByStateNot(Integer state);
}
