package  cn.light.server.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.light.entity.entity.SysOrder;
import cn.light.entity.mapper.OrderMapper;
import cn.light.entity.repository.OrderRepository;
import cn.light.server.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 自动生成 业绩数据 service 实现
 * @author : 二胡子
 * @date : 2024-07-17 23:06:09
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, SysOrder> implements OrderService {
        @Resource
        private  OrderRepository orderRepository;

}