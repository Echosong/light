package cn.light.admin.vo;

import cn.light.packet.dto.order.OrderListDTO;
import cn.light.packet.dto.order.OrderStatisticsDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

/**
 * 前端显示组合
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
public class OrderVO {
    //分页数据
    Page<OrderListDTO> page;

    //各项统计数据
    OrderStatisticsDTO statistics;

}
