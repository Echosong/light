package com.kdao.light.repository;

import com.kdao.light.entity.KdFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p>Title: </p >
 * <p>Description: 文件管理</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/29 002922:15</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Repository
public interface FileRepository extends JpaRepository<KdFile, Integer> {
    /**
     * 分页查询
     *
     * @param fileName 文件名
     * @param fileType 文件业务类型
     * @param pageable 分页条件
     * @return 数据
     */
    @Query(nativeQuery = true, value = "select * from kd_file  where " +
            " if(?1 != '', INSTR(file_name,?1), true) and if(IFNULL(?2, 1) = 1 , true, file_type=?2) ")
    Page<KdFile> listPage(String fileName, Integer fileType, Pageable pageable);


    /**
     * 根据文件路径获取信息
     * @param uuid uu
     * @return 文件
     */
    Optional<KdFile> findByUuid(String uuid);
}
