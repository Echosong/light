package cn.light.entity.repository;
import java.util.*;
import java.math.BigDecimal;
import cn.light.entity.entity.KdUser;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: 自动生成 用户</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 19:08:09</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Repository
@SuppressWarnings("ALL")
public interface UserRepository extends JpaRepository<KdUser, Integer> {
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