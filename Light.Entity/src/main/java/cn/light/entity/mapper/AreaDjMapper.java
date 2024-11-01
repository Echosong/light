package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.areaDj.AreaDjQueryDTO;
import cn.light.entity.entity.SysAreaDj;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 行政区划实体
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-10-05 21:10:39
 */
@Mapper
public interface AreaDjMapper extends BaseMapper<SysAreaDj> {
    List<SysAreaDj> listPage(AreaDjQueryDTO areaDjQueryDTO);
}
