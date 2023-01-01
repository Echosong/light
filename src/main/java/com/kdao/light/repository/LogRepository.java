package com.kdao.light.repository;

import com.kdao.light.entity.KdLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * <p>Title: 自动生成 日志</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2021-11-10 22:35:14</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Repository
public interface LogRepository extends JpaRepository<KdLog, Integer> {
  /**
   * 分页查询
   * @param description 描述
   * @param startCreateTime 开始时间
   * @param endCreateTime 结束时间
   * @param pageable 查询条件
   * @return 分页数据
   */
  @Query(nativeQuery = true, value = "select * from kd_log where true  and if(?1 !='', INSTR(description ,?1), true) and if(IFNULL(?2,-1)=-1,true, create_time >= ?2) and if(IFNULL(?3,-1)=-1,true , create_time <= ?3) ")
  Page<KdLog> listPage(String description, Date startCreateTime, Date endCreateTime, Pageable pageable);
}