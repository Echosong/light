package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.log.LogQueryDTO;
import cn.light.entity.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 日志
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface LogMapper extends BaseMapper<SysLog> {
    List<SysLog> listPage(LogQueryDTO logQueryDTO);
}
