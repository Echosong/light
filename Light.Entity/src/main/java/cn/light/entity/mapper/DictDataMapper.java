package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.dictData.DictDataQueryDTO;
import cn.light.entity.entity.SysDictData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 字典数据
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface DictDataMapper extends BaseMapper<SysDictData> {
    List<SysDictData> listPage(DictDataQueryDTO dictDataQueryDTO);
}
