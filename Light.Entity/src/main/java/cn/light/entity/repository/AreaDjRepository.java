package cn.light.entity.repository;
import java.util.*;
import java.math.BigDecimal;
import cn.light.entity.entity.SysAreaDj;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
* AreaDjRepository
* email:zq_songfeigang@163.com
*
* @author : 二胡子
* @version : 1.0
* @date : 2024-10-05 21:10:39
*/
@Repository
@SuppressWarnings("ALL")
public interface AreaDjRepository extends JpaRepository<SysAreaDj, Integer> {

}