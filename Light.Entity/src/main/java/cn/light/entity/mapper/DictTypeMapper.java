package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.dictType.DictTypeQueryDTO;
import cn.light.entity.entity.SysDictType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 字典类型
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface DictTypeMapper extends BaseMapper<SysDictType> {
    List<SysDictType> listPage(DictTypeQueryDTO dictTypeQueryDTO);
}
