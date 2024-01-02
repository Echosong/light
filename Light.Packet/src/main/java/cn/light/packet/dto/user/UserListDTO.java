package cn.light.packet.dto.user;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.PageInfo;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.*;

import cn.light.packet.enums.UserRegTypeEnum;
import cn.light.packet.enums.UserStateEnum;
import cn.light.packet.enums.UserSexEnum;

/**
 * <p>Title:用户 </p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 19:23:19</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserListDTO extends SysBaseDTO implements IDictionaryObject {
    @NotNull(message="{jakarta.validation.constraints.NotNull.message}")
    @Pattern(message="必须为手机格式",regexp="\\d{11}")
   @Schema(name="账号" )
    private String username;

    @Range(min=0,max=1)
   @Schema(name="性别" )
    @ApiModelPropertyEnum(UserSexEnum.class)
    private Integer sex;

    @Range(min=0,max=300)
   @Schema(name="身高" )
    private Double height;

   @Schema(name="体重" )
    private Double weight;

   @Schema(name="昵称" )
    private String nick;

   @Schema(name="注册方式" , description="0,1,2")
    @ApiModelPropertyEnum(UserRegTypeEnum.class)
    private Integer type;

    @Length(min=2,max=2147483647,message="姓名必须大于两个字符")
   @Schema(name="姓名" )
    private String name;

   @Schema(name="状态" , description="0,1")
    @ApiModelPropertyEnum(UserStateEnum.class)
    private Integer state;

    @Email(message="{jakarta.validation.constraints.Email.message}")
   @Schema(name="邮箱" )
    private String email;

   @Schema(name="注册ip" )
    private String regIp;

   @Schema(name="登录ip" )
    private String loginIp;


}
