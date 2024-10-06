package cn.light.entity.cache;

import cn.light.entity.entity.SysTableColumn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * TableColumnCacheRepository
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 * @date : 2024/8/3 8:27
 */
public interface TableColumnCacheRepository  extends JpaRepository<SysTableColumn, String> {
    Optional<SysTableColumn> findByUserIdAndTableName(Integer userId, String tableName);

}
