package cn.light.entity.repository;

import cn.light.entity.entity.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>Title: 自动生成 日志</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 19:08:10</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@SuppressWarnings("ALL")
public interface LogRepository extends JpaRepository<SysLog, Integer> {

}