package cn.light.entity.mapper;

import cn.light.packet.dto.order.OrderStatisticsDTO;
import cn.light.packet.dto.order.SumAmountByCompanyDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.order.OrderQueryDTO;
import cn.light.entity.entity.SysOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 自动生成 业绩数据
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface OrderMapper extends BaseMapper<SysOrder> {
    List<SysOrder> listPage(OrderQueryDTO orderQueryDTO);

    //统计
    OrderStatisticsDTO listPageStatistics(OrderQueryDTO orderQueryDTO);

    /**
     * 统计本月的饼图
     */
    List<SumAmountByCompanyDTO> SumAmountByCompany();
    BigDecimal selectSumAmount(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("userId") Integer userId);
}
