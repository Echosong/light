package cn.light.packet.dto.user;


import cn.light.common.anno.AutoEntityField;
import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.packet.enums.UserStateEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

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
@Schema(defaultValue = "注册用户信息")
@Data
public class RegDTO {

   @Schema(defaultValue = "账号直接用手机号")
    @NotNull
    @Column(unique = true, length = 11, name = "username")
    @Pattern(regexp ="\\d{11}", message = "必须为手机格式")
    private String username;

    @Length(min = 6, message = "密码必须大于等于6位")
   @Schema(defaultValue = "密码")
    private String password;

   @Schema(defaultValue = "姓名")
    @Length(min = 2, message = "姓名必须大于两个字符")
    private String name;

    @AutoEntityField("身高")
    @Range(min = 0, max = 2)
    private Double height;

    @AutoEntityField("体重")
    private Double weight;

    @AutoEntityField("昵称")
    private String nick;

   @Schema(defaultValue = "状态默认开启", allowableValues = "0,1,2" )
    @ApiModelPropertyEnum(value = UserStateEnum.class)
    private Integer state = 0 ;

   @Schema(defaultValue = "性别 0 男 1 女")
    private Integer sex;

   @Schema(defaultValue = "0 线下注册 1 微信注册， 2 支付宝小程序， 3 app")
    private String type;

   @Schema(defaultValue = "获取性别")
    public String getSexEnum(){
        return Objects.equals(sex, 0)?"男":"女";
    }

   @Schema(defaultValue = "email")
    private String email;

    @AutoEntityField("身份证正面")
    private String frontCard;

    @AutoEntityField("身份证反面")
    private String reverseCard;

}
