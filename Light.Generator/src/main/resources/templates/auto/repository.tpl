package #{PackageName}#.repository;
import java.util.*;
import java.math.BigDecimal;
import #{SYS_PATH}.entity.entity.#{UpTableName}#;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * #{UpEntityName}#Repository
 *
 * @author : 二胡子
 * @date : #{localDate}#
 */
@Repository
@SuppressWarnings("ALL")
public interface #{UpEntityName}#Repository extends JpaRepository<#{UpTableName}#, Integer> {

}