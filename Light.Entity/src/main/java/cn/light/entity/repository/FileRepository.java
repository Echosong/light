package cn.light.entity.repository;
import java.util.*;

import cn.light.entity.entity.SysFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: 自动生成 文件管理</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 19:08:09</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Repository
@SuppressWarnings("ALL")
public interface FileRepository extends JpaRepository<SysFile, Integer> {
    /**
     * 根据文件路径获取信息
     * @param uuid uu
     * @return 文件
     */
    Optional<SysFile> findByUuid(String uuid);
}