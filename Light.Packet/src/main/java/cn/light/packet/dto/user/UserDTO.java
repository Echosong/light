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

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.*;

import cn.light.packet.enums.UserSexEnum;
import cn.light.packet.enums.UserStateEnum;

/**
 * <p>Title: 用户</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-04-05 01:00:52</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends SysBaseDTO implements IDictionaryObject {
    @NotNull(message = "{jakarta.validation.constraints.NotNull.message}")
    @Schema(title = "账号")
    private String username;

    @Pattern(message = "必须为11位手机号", regexp = "\\d{11}")
    @Schema(title = "手机号")
    private String mobile;

    @Length(min = 6, max = 2147483647, message = "密码必须大于等于6位")
    @Schema(title = "密码")
    private String password;

    @Range(min = 0, max = 1)
    @Schema(title = "性别")
    @ApiModelPropertyEnum(UserSexEnum.class)
    private Integer sex;

    @Schema(title = "工号")
    private String code;

    @Length(min = 2, max = 2147483647, message = "姓名必须大于两个字符")
    @Schema(title = "姓名")
    private String name;

    @Schema(title = "状态", description = "0,1")
    @ApiModelPropertyEnum(UserStateEnum.class)
    private Integer state;

    @Email(message = "{jakarta.validation.constraints.Email.message}")
    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "注册ip")
    private String regIp;

    @Schema(title = "登录ip")
    private String loginIp;

    @Schema(title = "个人简介")
    private String info;

    @Schema(title = "角色id")
    private Integer roleId;

    @Schema(title = "角色名称")
    private String roleName;


}
