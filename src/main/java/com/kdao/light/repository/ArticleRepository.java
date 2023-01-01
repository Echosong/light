package com.kdao.light.repository;
import java.util.*;
import java.math.BigDecimal;
import com.kdao.light.entity.KdArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: 自动生成 球场</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2022-09-21 15:32:32</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Repository
public interface ArticleRepository extends JpaRepository<KdArticle, Integer> {
  /**
     * 分页查询
     * @param pageable 分页条件
     * @return 分页数据
     */
    @Query(value = "select t1 from KdArticle t1 where 1=1 ")
    Page<KdArticle> listPage( Pageable pageable);

  List<KdArticle> findByType(int type);
}