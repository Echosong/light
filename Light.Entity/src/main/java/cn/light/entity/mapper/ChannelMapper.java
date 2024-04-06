package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.channel.ChannelQueryDTO;
import cn.light.entity.entity.SysChannel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 渠道信息表
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface ChannelMapper extends BaseMapper<SysChannel> {
    List<SysChannel> listPage(ChannelQueryDTO channelQueryDTO);
}
