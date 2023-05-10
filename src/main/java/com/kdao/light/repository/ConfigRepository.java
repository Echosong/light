package com.kdao.light.repository;

import com.kdao.light.entity.KdConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0518:35</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Repository
@SuppressWarnings("ALL")
public interface ConfigRepository extends JpaRepository<KdConfig, Integer> {

}
