package cn.light.admin.controller;
import cn.light.common.annotation.*;
import cn.light.common.consts.Consts;
import cn.light.packet.dto.order.OrderDTO;
import cn.light.packet.dto.order.OrderListDTO;
import cn.light.packet.dto.order.OrderQueryDTO;
import cn.light.server.service.OrderService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.*;

/**
 *  订单通用信息 控制器
 *  email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @date : 2026-07-04 11:26:22
 */
@Tag(name = "订单通用信息 控制器")
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController{

    @Resource
    private OrderService orderService;

    @Operation(summary = "分页查询订单通用信息")
    @PutMapping("/listPage")
    public Page<OrderListDTO> listPage(@RequestBody @Valid OrderQueryDTO queryDTO){
        return orderService.listPage(queryDTO);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出订单通用信息")
    public ResponseEntity<byte[]> export(@RequestBody @Valid OrderQueryDTO queryDTO) {
        return orderService.export(queryDTO);
    }

    @Operation(summary = "新增活更新订单通用信息")
    @PostMapping("/save")
    @Log("新增|修改订单通用信息")
    public void save(@RequestBody @Valid OrderDTO orderDTO){
         orderService.save(orderDTO);
    }


    @Operation(summary = "查询单个明细")
    @GetMapping("/find/{id}")
    public OrderDTO find(@PathVariable Integer id){
        return orderService.find(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除订单通用信息")
    @Permission(roles = Consts.ROLE_ADMIN_CODE)
    public void delete(@PathVariable(value="id") Integer id) {
        orderService.delete(id);
    }


}
