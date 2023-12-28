package com.kdao.light.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kdao.light.common.dto.config.ConfigQueryDTO;
import com.kdao.light.entity.KdConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 系统配置
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface ConfigMapper extends BaseMapper<KdConfig> {
    List<KdConfig> listPage(ConfigQueryDTO configQueryDTO);
}
