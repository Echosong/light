package cn.light.entity.entity;

import cn.light.common.anno.*;
import cn.light.common.enums.HtmlTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Description: 商品
 * @author : 二胡子
 * @version :1.0.0
 */

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "商品")
public class SdProduct extends SysBase{

    /** 商户Id(0为总后台管理员创建,不为0的时候是商户后台创建) */
    @AutoEntityField( value = "供货商",htmlType = HtmlTypeEnum.SELECT, source = "Supplier" )
    @NotinListDTO
    private Integer supplierId;

    @AutoEntityField(value = "供货商名称")
    private String supplierName;


    /** 商品名称 */
    @NotNull
    @NotBlank(message = "商品名称不能空")
    @AutoEntityField(value = "商品名称")
    @InQueryDTO
    private String storeName;

    /** 商品编码 */
    @AutoEntityField(value = "商品编码")
    @NotinListDTO
    private String storeCode;

    /** 分类id */
    @NotNull
    @NotBlank(message = "商品分类不能空")
    @AutoEntityField(value = "商品分类", htmlType = HtmlTypeEnum.SELECT, source = "ProductCategory")
    @NotinListDTO
    private String cateId;

    @AutoEntityField(value = "分类名称")
    @InQueryDTO
    private String cateName;


    /** 商品主图 */
    @NotNull
    @NotBlank(message = "请上传商品主图")
    @AutoEntityField(value = "商品主图", htmlType = HtmlTypeEnum.UPLOAD)
    private String image;

    /** 商品轮播图 */
    @NotNull
    @NotBlank(message = "请上传商品轮播图")
    @AutoEntityField(value = "商品轮播图", htmlType = HtmlTypeEnum.UPLOAD)
    @NotinListDTO
    private String sliderImage;


    /** 商品简介 */
    @AutoEntityField(value = "商品简介", htmlType = HtmlTypeEnum.TEXTAREA)
    @NotinListDTO
    private String storeInfo;

    /** 产品描述 */
    @NotNull
    @NotBlank(message = "请填写商品详情")
    @AutoEntityField(value = "商品详情", htmlType = HtmlTypeEnum.TEXTEDIT)
    @Column(columnDefinition = " text comment '商品详情'")
    @NotinListDTO
    private String description;
    /** 体积 */
    @AutoEntityField(value = "体积")
    private BigDecimal volume;

    /** 重量 */
    @AutoEntityField(value = "重量")
    private BigDecimal weight;

    /** 排序 */
    @AutoEntityField(value = "排序")
    @NotinListDTO
    private Integer sort;


    /** 销量 */
    @AutoEntityField(value = "销量")
    private Integer sales;


    /** 状态（0：草稿，1：已下架，2：上架审核中，3:已上架，4:审核拒绝）*/
    @NotNull
    @AutoEntityField(value = "状态", notes = "状态（0：草稿，1：已下架，2：上架审核中，3:已上架，4:审核拒绝）", enums = GoodStateEnum.class)
    private Integer state = 0;

    /** 拒绝备注 */
    @NotinListDTO
    private String refuseRemark;

    /** 是否热卖 */
    @AutoEntityField(value = "热卖", notes = "是否热卖（0：否，1：是）",enums = HotEnum.class)
    private Integer isHot;


    /** 是否新品 */
    @AutoEntityField(value = "新品", notes = "是否新品（0：否，1：是）",enums = YesOrNoEnum.class)
    private Integer isNew;


    /** 是否包邮 */
    @AutoEntityField(value = "是否包邮",notes = "是否包邮（0：否，1：是）",enums = YesOrNoEnum.class)
    private Integer isPostage;

    /** 邮费 */
    @AutoEntityField(value = "邮费")
    @NotinListDTO
    private BigDecimal postage;

    @AutoEntityField(value = "邮费模版ID")
    @NotinListDTO
    private Integer tempId;

    @AutoEntityField(value = "是否开启积分兑换",notes = "是否开启积分兑换（0：否，1：是）",enums = YesOrNoEnum.class)
    @NotinListDTO
    private Integer isIntegral;

    /** 需要多少积分兑换 */
    @AutoEntityField( value ="积分兑换",  notes = "需要多少积分兑换 只在开启积分兑换时生效")
    @NotinListDTO
    private Integer integral;
    //可使用积分方案
    @AutoEntityField(value = "可使用积分方案 50:15")
    private String pointRule;
    /** 获得积分 */
    @AutoEntityField(value = "获得积分")
    @DecimalMin(value="0.00", message = "获得积分不在合法范围内" )
    @DecimalMax(value="99999999.99", message = "获得积分不在合法范围内")
    private BigDecimal giveIntegral;


    /** 秒杀状态 0 未开启 1已开启 */
    @AutoEntityField(value = "秒杀状态",notes = "是否开启秒杀（0：未开启，1：已开启）",enums = YesOrNoEnum.class)
    private Integer isSeckill;
    @AutoEntityField(value = "秒杀开始时间")
    private String seckillStartTime;
    @AutoEntityField(value = "秒杀价")
    private BigDecimal skillPrice;
    @AutoEntityField(value = "秒杀结束时间")
    private String seckillEndTime;

    /** 拼团状态 0 未开启 1已开启 */
    @AutoEntityField(value = "拼团状态",notes = "是否开启拼团（0：未开启，1：已开启）",enums = YesOrNoEnum.class)
    private Integer isGroup;

    @AutoEntityField(value = "总团数")
    private Integer groupNum;
    @AutoEntityField(value = "每团人数")
    private Integer groupPeopleNum;
    @AutoEntityField(value = "拼团价格")
    private BigDecimal groupPrice;
    @AutoEntityField(value = "成团时长,单位天")
    private Integer groupDuration;

    /** 是否优品推荐 */
    @AutoEntityField(value = "推荐", notes = "是否优品推荐（0：否，1：是）",enums = YesOrNoEnum.class)
    private Integer isGood;

    @AutoEntityField(value = "价格",notes="取规格最小价格")
    private BigDecimal price;

    @AutoEntityField(value = "市场价",notes="取规格最小市场价格")
    private BigDecimal otPrice;

    @AutoEntityField(value = "库存",notes="取规格总库存")
    private Integer stock;

    /** 虚拟销量 */
    @AutoEntityField(value = "虚拟销量")
    private Integer ficti;


    /** 虚拟浏览量 */
    @AutoEntityField(value = "虚拟浏览量")
    private Integer browse;

    @AutoEntityField(value = "告警阀值")
    private Integer alarmThresholdValue = 1;

    @NotNull
    @AutoEntityField(value = "规格",notes = "规格（1：统一规格，2：多规格）",enums = ProductRuleEnum.class)
    @NotinListDTO
    private Integer specType = 1;

    @AutoEntityField(value = "是否单独分佣")
    @NotinListDTO
    private Integer isSub;

    //收藏量
    @AutoEntityField(value = "收藏量")
    @AutoEntityFieldDefault("0")
    private Integer collect;


}
