package cn.light.entity.repository;
import java.util.*;
import java.math.BigDecimal;
import cn.light.entity.entity.SysCode;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
* CodeRepository
* email:zq_songfeigang@163.com
*
* @author : 二胡子
* @version : 1.0
* @date : 2024-07-28 22:08:05
*/
@Repository
@SuppressWarnings("ALL")
public interface CodeRepository extends JpaRepository<SysCode, Integer> {

}