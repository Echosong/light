package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.enums.HtmlTypeEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 商品规则
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "商品规格")
public class SdProductRule extends  SysBase {

    /** 商品编号 */
    @AutoEntityField(value = "商品编号")
    private int productId;

    /** 规格名称 */
    @NotBlank(message = "请输入规格名称")
    @AutoEntityField(value = "规格名称")
    private String ruleName;

    /** 规格值 */
    @NotBlank(message = "请输入规格值")
    @AutoEntityField(value = "规格值")
    private String ruleValue;

    /** 图片 */
    @AutoEntityField(value = "图片", htmlType = HtmlTypeEnum.UPLOAD)
    private String image;

    /** 成本价 */
    @AutoEntityField(value = "成本价")
    @NotNull(message = "请输入成本价")
    @DecimalMin(value="0.00", message = "成本价不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "成本价不在合法范围内")
    private BigDecimal cost;

    /** 市场价 */
    @AutoEntityField(value = "市场价")
    @NotNull(message = "请输入市场价")
    @DecimalMin(value="0.00", message = "市场价不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "市场价不在合法范围内")
    private BigDecimal otPrice;

    //结算价
    @AutoEntityField(value = "结算价")
    @NotNull(message = "请输入结算价")
    @DecimalMin(value="0.00", message = "结算价不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "结算价不在合法范围内")
    private BigDecimal settlePrice;


    /** 会员价格 */
    @AutoEntityField(value = "会员价格")
    private BigDecimal price;

    /** 库存 */
    @AutoEntityField(value = "库存")
    @NotNull(message = "请输入库存")
    @Min(message = "库存不能小于0",value = 1)
    private Integer stock;


    /** 体积 */
    @AutoEntityField(value = "体积")
    private BigDecimal volume;

    /** 重量 */
    @AutoEntityField(value = "重量")
    private BigDecimal weight;

    //拼团价
    @AutoEntityField(value = "拼团价")
    private BigDecimal groupPrice;

    @AutoEntityField(value = "秒杀价")
    private BigDecimal skillPrice;

}
