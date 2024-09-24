package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.AutoEntityFieldDefault;
import cn.light.common.anno.InQueryDTO;
import cn.light.packet.enums.business.ExpressCompanyEnum;
import cn.light.packet.enums.business.OrderStateEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

/**
 * 商城订单
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "商城订单")
//@AutoCover(value = {CodeTypeEnum.DTO, CodeTypeEnum.LIST_DTO})
public class SdOrderProduct extends SysOrder{

    @AutoEntityField(value = "产品重量")
    private BigDecimal weight;

    @AutoEntityField(value = "快递价格")
    private BigDecimal postPrice;

    @AutoEntityField(value = "收货地址")
    private Integer memberAddressId;

    @AutoEntityField(value = "订单状态", enums = OrderStateEnum.class)
    @InQueryDTO
    private Integer orderStatus;

    //快递公司
    @AutoEntityField(value = "快递公司", enums = ExpressCompanyEnum.class)
    private Integer expressCompany;

    //快递单号
    @AutoEntityField(value = "快递单号")
    private String expressNo;

    @AutoEntityField(value = "退款状态")
    @InQueryDTO
    private Integer refundState;

    //退款金额
    @AutoEntityField(value = "退款金额")
    @AutoEntityFieldDefault("0")
    private BigDecimal refundAmount;


}
