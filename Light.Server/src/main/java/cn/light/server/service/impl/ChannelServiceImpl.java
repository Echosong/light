package  cn.light.server.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.light.entity.entity.SysChannel;
import cn.light.entity.mapper.ChannelMapper;
import cn.light.entity.repository.ChannelRepository;
import cn.light.server.service.ChannelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 自动生成 渠道信息表 service 实现
 * @author : 二胡子
 * @date : 2024-07-17 23:06:08
 */
@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, SysChannel> implements ChannelService {
        @Resource
        private  ChannelRepository channelRepository;

}