package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.order.OrderQueryDTO;
import cn.light.entity.entity.SysOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 业绩数据
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface OrderMapper extends BaseMapper<SysOrder> {
    List<SysOrder> listPage(OrderQueryDTO orderQueryDTO);
}
