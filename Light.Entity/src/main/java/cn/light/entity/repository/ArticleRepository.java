package cn.light.entity.repository;

import cn.light.entity.entity.KdArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: 自动生成 新闻</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 19:08:09</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Repository
@SuppressWarnings("ALL")
public interface ArticleRepository extends JpaRepository<KdArticle, Integer> {

}