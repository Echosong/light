package com.kdao.light.repository;
import java.util.*;
import java.math.BigDecimal;
import com.kdao.light.entity.KdProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: 自动生成 产品信息</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2023-12-28 21:43:55</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Repository
@SuppressWarnings("ALL")
public interface ProductRepository extends JpaRepository<KdProduct, Integer> {


}