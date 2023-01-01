package com.kdao.light.common.dto.user;

import com.kdao.autocode.anno.AutoEntityField;
import com.kdao.light.common.annotation.ApiModelPropertyEnum;
import com.kdao.light.common.enums.UserStateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * <p>Title: LightController</p >
 * <p>Description: RegDTO</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2022-09-29 9:59 </p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@ApiModel("注册用户信息")
@Data
public class RegDTO {

    @ApiModelProperty("账号直接用手机号")
    @NotNull
    @Column(unique = true, length = 11, name = "username")
    @Pattern(regexp ="\\d{11}", message = "必须为手机格式")
    private String username;

    @Length(min = 6, message = "密码必须大于等于6位")
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("姓名")
    @Length(min = 2, message = "姓名必须大于两个字符")
    private String name;

    @AutoEntityField("身高")
    @Range(min = 0, max = 2)
    private Double height;

    @AutoEntityField("体重")
    private Double weight;

    @AutoEntityField("昵称")
    private String nick;

    @ApiModelProperty(value = "状态默认开启", allowableValues = "0,1,2" )
    @ApiModelPropertyEnum(value = UserStateEnum.class)
    private Integer state = 0 ;

    @ApiModelProperty("性别 0 男 1 女")
    private Integer sex;

    @ApiModelProperty("0 线下注册 1 微信注册， 2 支付宝小程序， 3 app")
    private String type;

    @ApiModelProperty("获取性别")
    public String getSexEnum(){
        return Objects.equals(sex, 0)?"男":"女";
    }

    @ApiModelProperty("email")
    private String email;

    @AutoEntityField("身份证正面")
    private String frontCard;

    @AutoEntityField("身份证反面")
    private String reverseCard;

}
