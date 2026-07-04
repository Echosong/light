package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.order.OrderQueryDTO;
import cn.light.entity.entity.SysOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 订单通用信息
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2026-07-04 11:26:22
 */
@Mapper
public interface OrderMapper extends BaseMapper<SysOrder> {
    List<SysOrder> listPage(OrderQueryDTO orderQueryDTO);
}
