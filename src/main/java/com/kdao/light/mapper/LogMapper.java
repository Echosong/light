package com.kdao.light.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kdao.light.common.dto.log.LogQueryDTO;
import com.kdao.light.entity.KdLog;
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
