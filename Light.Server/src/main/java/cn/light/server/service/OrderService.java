package  cn.light.server.service;

import cn.light.entity.entity.SysOrder;
import cn.light.packet.dto.order.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * 自动生成 订单通用信息 service 接口
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2026-07-04 11:26:22
 */
public interface OrderService extends IService<SysOrder> {

    Page<OrderListDTO> listPage(OrderQueryDTO queryDTO);

    ResponseEntity<byte[]> export(OrderQueryDTO queryDTO);

    OrderDTO save(OrderDTO orderDTO);

    OrderDTO find(Integer id);

    void delete(Integer id);


}
