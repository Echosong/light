package cn.light.admin.controller;
import cn.light.common.annotation.*;
import cn.light.entity.entity.SysChannel;
import cn.light.entity.repository.ChannelRepository;
import cn.light.packet.dto.order.OrderDTO;
import cn.light.packet.dto.order.OrderListDTO;
import cn.light.packet.dto.order.OrderQueryDTO;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.*;
import cn.light.entity.entity.SysOrder;
import cn.light.entity.mapper.OrderMapper;
import cn.light.entity.repository.OrderRepository;
import cn.light.server.service.UserService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.io.IOException;
import java.util.*;

/**
 * <p>Title: </p >
 * <p>Description: 业绩数据管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-04-04 10:56:58</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "业绩数据 控制器")
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController{
    @Resource
    private  OrderRepository orderRepository;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private UserService userService;
    @Resource
    private ChannelRepository channelRepository;

    @Operation(summary = "分页查询业绩数据")
    @PutMapping("/listPage")
    public Page<OrderListDTO> listPage(@RequestBody @Valid OrderQueryDTO queryDTO){
        Page<SysOrder> dataPages  =  PageUtil.getPage(orderMapper::listPage, queryDTO);
        return DtoMapper.convertPage(dataPages, OrderListDTO.class);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出业绩数据")
    public ResponseEntity<byte[]> export(@RequestBody @Valid OrderQueryDTO queryDTO) throws IOException, IllegalAccessException {
        List<SysOrder> all = orderMapper.listPage(queryDTO);
        String fileName = "Order"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        return ExcelUtil.generateImportFile(DtoMapper.convertList(all, OrderListDTO.class), fileName, OrderListDTO.class);
    }

    @Operation(summary = "新增活更新业绩数据")
    @PostMapping("/save")
    @Log("新增|修改业绩数据")
    public void save(@RequestBody @Valid OrderDTO orderDTO){
        SysOrder sysOrder = DtoMapper.convert(orderDTO, SysOrder.class);
        sysOrder.setOperation(userService.getUserCache().getId());
        SysChannel channel = channelRepository.findById(orderDTO.getChannelId()).orElseThrow(() -> new BaseKnownException(500, "渠道不存在"));

        //三类利润
        BigDecimal threePrice = (sysOrder.getThreeClassPrice().subtract(sysOrder.getThreeClassChannelPrice())).multiply(new BigDecimal(sysOrder.getThreeClass()));
        //四类利润
        BigDecimal fourPrice = (sysOrder.getFourClassPrice().subtract(sysOrder.getFourClassChannelPrice())).multiply(new BigDecimal(sysOrder.getFourClass()));
        //五类利润
        BigDecimal fivePrice = (sysOrder.getFiveClassPrice().subtract(sysOrder.getFiveClassChannelPrice())).multiply(new BigDecimal(sysOrder.getFiveClass()));
        //返费总和
        sysOrder.setTotalRebate(threePrice.add(fourPrice).add(fivePrice));

        //三类陈本差
        BigDecimal threeDiffPrice = sysOrder.getThreeClassPrice().subtract(channel.getThreeClassCostPrice()).multiply(new BigDecimal(sysOrder.getThreeClass()));
        //四类成本差
        BigDecimal fourDiffPrice = sysOrder.getFourClassPrice().subtract(channel.getFourClassCostPrice()).multiply(new BigDecimal(sysOrder.getFourClass()));
        //五类成本差
        BigDecimal fiveDiffPrice = sysOrder.getFiveClassPrice().subtract(channel.getFiveClassCostPrice()).multiply(new BigDecimal(sysOrder.getFiveClass()));
        //利润
        sysOrder.setProfit(threeDiffPrice.add(fourDiffPrice).add(fiveDiffPrice).subtract(sysOrder.getTotalRebate()));

        orderRepository.save(sysOrder);
    }

    @Operation(summary = "查询全部业绩数据")
    @GetMapping("/list")
    public List<OrderListDTO> list(){
        List<SysOrder> all = orderRepository.findAll();
        return DtoMapper.convertList(all, OrderListDTO.class);
    }

    @Operation(summary = "查询")
    @GetMapping("/find/{id}")
    public OrderDTO find(@PathVariable(value = "id") Integer id){
        SysOrder one = orderRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, OrderDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除业绩数据")
    public void delete(@PathVariable Integer id) {
        orderRepository.deleteById(id);
    }

    

}