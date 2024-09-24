package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.Pattern;

/**
 * 用户收货地址
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "用户收货地址")
public class SdMemberAddress extends  SysBase{


    @AutoEntityField(value = "用户id")
    private Integer memberId;

    @AutoEntityField(value = "用户账号")
    private String memberName;

    @AutoEntityField(value = "收货人姓名")
    private String realName;

    @AutoEntityField(value = "收货人电话")
    @Pattern(regexp = "\\d{11}", message = "手机号码格式错误")
    private String phone;

    @AutoEntityField(value = "收货人所在省")
    private String province;

    @AutoEntityField(value = "收货人所在市")
    private String city;

    @AutoEntityField(value = "收货人所在市id")
    private Integer cityId;

    @AutoEntityField(value = "收货人所在区")
    private String district;

    @AutoEntityField(value = "收货人详细地址")
    private String detail;

    @AutoEntityField(value = "邮编")
    private String postCode;

    @AutoEntityField(value = "经度")
    private String longitude;

    @AutoEntityField(value = "纬度")
    private String latitude;

    @AutoEntityField(value = "是否默认")
    private Integer isDefault;
}
