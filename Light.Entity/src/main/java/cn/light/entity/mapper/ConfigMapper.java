package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.config.ConfigQueryDTO;
import cn.light.entity.entity.KdConfig;
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
