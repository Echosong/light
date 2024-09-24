package cn.light.entity.entity;

import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.AutoEntityFieldDefault;
import cn.light.common.anno.InQueryDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单通用信息
 * @author : 二胡子
 * @version :1.0.0
 */
@Setter
@Getter
@MappedSuperclass
public class SysOrder extends  SysBase{
    @AutoEntityField(value = "用户账号")
    @InQueryDTO
    private String memberName;

    @AutoEntityField(value = "订单号")
    @InQueryDTO
    private String orderNo;

    @AutoEntityField(value = "用户ID")
    private Integer memberId;


    @AutoEntityField(value = "外部订单号")
    @InQueryDTO
    private String outOrderNo;


    @AutoEntityField(value = "市场价")
    private BigDecimal marketAmount;

    //定金
    @AutoEntityField(value = "定金")
    private BigDecimal earnestAmount;

    //协议价（成本价）
    @AutoEntityField(value = "协议价")
    private BigDecimal costAmount;

    @Schema(title = "订单价格")
    private BigDecimal orderPrice;

    @AutoEntityField(value = "支付金额")
    @InQueryDTO
    private BigDecimal payAmount;

    @AutoEntityField(value = "支付时间")
    private Date payTime;

    @AutoEntityField(value = "消费积分")
    @AutoEntityFieldDefault(value = "0")
    private Integer point;

    //赠送积分
    @AutoEntityField(value = "赠送积分")
    @AutoEntityFieldDefault(value = "0")
    private Integer givePoint;

    @AutoEntityField(value = "订单标题")
    private String title;

    @AutoEntityField(value = "供应商ID")
    private Integer supplierId;

    @AutoEntityField(value = "供应商名称")
    private String shopName;


    @AutoEntityField(value = "支付相关id")
    private Integer financePayId;


    @AutoEntityField(value = "发票抬头id")
    private Integer invoiceHeadId;

}
