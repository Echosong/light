package #{PackageName}#.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import #{SYS_PATH}.common.dto.#{EntityName}#.#{UpEntityName}#QueryDTO;
import #{SYS_PATH}.entity.entity.#{UpTableName}#;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 #{tableInfo}#
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface #{UpEntityName}#Mapper extends BaseMapper<#{UpTableName}#> {
    List<#{UpTableName}#> listPage(#{UpEntityName}#QueryDTO #{EntityName}#QueryDTO);
}
