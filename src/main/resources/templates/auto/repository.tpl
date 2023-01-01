package #{PackageName}#;
import java.util.*;
import java.math.BigDecimal;
import #{SYS_PATH}.entity.#{UpTableName}#;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: 自动生成 #{tableInfo}#</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: #{localDate}#</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Repository
public interface #{UpEntityName}#Repository extends JpaRepository<#{UpTableName}#, Integer> {
  /**
     * 分页查询
     #{pageable_params}#
     * @return 分页数据
     */
    @Query(value = "select t1 from #{UpTableName}# t1 #{queryWhere}#")
    Page<#{UpTableName}#> listPage(#{queryFields}# Pageable pageable);
}