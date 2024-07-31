package cn.light.entity.repository;
import java.util.*;
import java.math.BigDecimal;
import cn.light.entity.entity.SysTableColumn;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
* TableColumnRepository
* email:zq_songfeigang@163.com
*
* @author : 二胡子
* @version : 1.0
* @date : 2024-07-28 22:43:24
*/
@Repository
@SuppressWarnings("ALL")
public interface TableColumnRepository extends JpaRepository<SysTableColumn, Integer> {
    Optional<SysTableColumn> findByUserIdAndTableName(Integer userId, String tableName);

}