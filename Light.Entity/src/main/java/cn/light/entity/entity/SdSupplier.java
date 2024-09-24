package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.AutoEntityFieldDefault;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.enums.HtmlTypeEnum;
import cn.light.packet.enums.system.YesOrNoEnum;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 供货商 商城普通商品供货商
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "普通商品供货商")
public class SdSupplier extends SysBase{
    @AutoEntityField(value = "名称",isKeyName = true)
    @NotNull(message = "名称不能为空")
    @InQueryDTO
    private String name;

    @Pattern(regexp = "^[0-9]{11}$", message = "手机号码格式错误")
    @InQueryDTO
    private String phone;

    @AutoEntityField(value = "备注")
    private String remark;

    @AutoEntityField(value = "公司名称")
    @NotNull
    private String companyName;

    @AutoEntityField(value = "地址")
    private String  address;


    @AutoEntityField(value = "保证金")
    @AutoEntityFieldDefault(value = "0")
    private BigDecimal margin;

    @AutoEntityField(value = "加盟费")
    private BigDecimal inPrice;

    @AutoEntityField(value = "缴纳加盟费时间")
    private Date inPriceTime;
    @AutoEntityField(value = "到期时间")
    private Date expireTime;

    @AutoEntityField(value = "邮箱")
    private String email;

    @AutoEntityField(value = "店铺名称")
    private String shopName;

    @AutoEntityField(value = "店铺图片", htmlType = HtmlTypeEnum.UPLOAD)
    private String shopBanner;

    @AutoEntityField(value = "店铺评分")
    private Double shopStar;

    @AutoEntityField(value = "用户头像")
    private String avatar;

    @AutoEntityField(value = "发货地址")
    private String shopAddress;

    @AutoEntityField(value = "店铺发货城市")
    private Integer shopCityId;

    @AutoEntityField(value = "来源关联用户")
    private Integer memberId;

    @AutoEntityField(value = "城市编号")
    private Integer cityId;
    @AutoEntityField(value = "城市名称")
    private String cityName;


    @AutoEntityField(value = "材料")
    private String material;

    @AutoEntityField(value = "状态",enums= YesOrNoEnum.class)
    private Integer state;

    @AutoEntityField(value = "销量")
    @AutoEntityFieldDefault("0")
    private Integer saleCount;
}
