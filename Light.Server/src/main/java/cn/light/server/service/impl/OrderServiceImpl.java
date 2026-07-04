package  cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.ExcelUtil;
import cn.light.common.util.PageUtil;

import org.springframework.stereotype.Service;
import cn.light.entity.entity.SysOrder;
import cn.light.entity.mapper.OrderMapper;
import cn.light.server.service.OrderService;
import cn.light.packet.dto.order.*;

import org.springframework.http.ResponseEntity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.data.domain.Page;

import java.util.*;

/**
 * 自动生成 订单通用信息 service 实现
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2026-07-04 11:26:22
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, SysOrder> implements OrderService {

    @Override
    public Page<OrderListDTO> listPage(OrderQueryDTO queryDTO){
         Page<SysOrder> dataPages  =  PageUtil.getPage(this.baseMapper::listPage, queryDTO);
         return DtoMapper.convertPage(dataPages, OrderListDTO.class);
    }

    @Override
    public ResponseEntity<byte[]> export(OrderQueryDTO queryDTO) {
        List<SysOrder> all = this.baseMapper.listPage(queryDTO);
        String fileName = "Order"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        try {
            return ExcelUtil.generateImportFile(DtoMapper.convertList(all, OrderListDTO.class), fileName, OrderListDTO.class);
        }catch (Exception e) {
            throw new BaseKnownException(e.getMessage());
        }
    }

    @Override
    public OrderDTO save(OrderDTO saveDTO) {
        SysOrder order = DtoMapper.convert(saveDTO, SysOrder.class);
        this.saveOrUpdate(order);
        return DtoMapper.convert(order, OrderDTO.class);
    }

    @Override
    public void delete(Integer id) {
        this.removeById(id);
    }

    @Override
    public OrderDTO find(Integer id){
        SysOrder one = Optional.of(id).map(this::getById)
                             .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, OrderDTO.class);
    }



}
