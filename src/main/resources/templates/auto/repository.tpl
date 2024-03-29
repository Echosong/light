package #{PackageName}#;
import java.util.*;
import java.math.BigDecimal;
import #{SYS_PATH}.entity.#{UpTableName}#;
import com.github.lkqm.spring.jpa.repository.MybatisQuery;
import org.springframework.data.domain.Page;
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
@SuppressWarnings("ALL")
public interface #{UpEntityName}#Repository extends JpaRepository<#{UpTableName}#, Integer> {

}