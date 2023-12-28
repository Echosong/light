package com.kdao.light.repository;

import com.kdao.light.entity.KdConfig;
import com.kdao.light.entity.KdLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>Title: light</p >
 * <p>Description: LogRepository</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2023/12/28 21:29 </p >
 *
 * @author : 二胡子
 * @version :1.0.0
 */
public interface LogRepository extends JpaRepository<KdLog, Integer> {
}
