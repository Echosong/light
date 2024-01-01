package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.common.dto.log.LogQueryDTO;
import cn.light.entity.entity.KdLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 日志
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface LogMapper extends BaseMapper<KdLog> {
    List<KdLog> listPage(LogQueryDTO logQueryDTO);
}
