package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

/**
 * 购物车
 * @author : 二胡子
 * @version :1.0.0
 */

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "购物车", controller = false,  viewList = false, viewFrom = false, viewInfo = false)
public class SdProductCar extends  SysBase{

    @AutoEntityField(value = "会员id")
    private Integer memberId;

    @AutoEntityField(value = "商品id")
    private Integer productId;

    @AutoEntityField(value = "商品名称")
    private String productName;

    //商品图片
    @AutoEntityField(value = "商品图片")
    private String productImg;

    @AutoEntityField(value = "商品skuId")
    private Integer skuId;

    @AutoEntityField(value = "商品sku名称")
    private String skuName;

    @AutoEntityField(value = "商品数量")
    private Integer num;

    @AutoEntityField(value = "店铺名称")
    private String shopName;

    @AutoEntityField(value = "商品价格")
    private BigDecimal price;

    @AutoEntityField(value = "商品市场价")
    private BigDecimal marketPrice;

    @AutoEntityField(value = "供应商id")
    private Integer supplierId;
}
