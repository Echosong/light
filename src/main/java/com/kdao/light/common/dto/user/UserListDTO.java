package com.kdao.light.common.dto.user;

import com.kdao.light.common.annotation.ApiModelPropertyEnum;
import com.kdao.light.common.component.IDictionaryObject;
import com.kdao.light.common.dto.PageInfo;
import com.kdao.light.common.dto.SysBaseDTO;
import com.kdao.light.common.enums.FileTypeEnum;
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

import com.kdao.light.common.enums.UserSexEnum;
import com.kdao.light.common.enums.UserRegTypeEnum;
import com.kdao.light.common.enums.UserStateEnum;

/**
 * <p>Title:用户 </p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2023-12-30 14:13:14</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserListDTO extends SysBaseDTO implements IDictionaryObject {
    @NotNull
    @Pattern(message = "必须为手机格式", regexp = "\\d{11}")
    @Schema(name = "账号")
    private String username;

    @Range(min = 0, max = 1)
    @Schema(name = "性别")
    @ApiModelPropertyEnum(UserSexEnum.class)
    private Integer sex;

    @Range(min = 0, max = 300)
    @Schema(name = "身高")
    private Double height;

    @Schema(name = "体重")
    private Double weight;

    @Schema(name = "昵称")
    private String nick;

    @Schema(name = "注册方式", description = "0,1,2")
    @ApiModelPropertyEnum(UserRegTypeEnum.class)
    private Integer type;

    @Length(min = 2, max = 2147483647, message = "姓名必须大于两个字符")
    @Schema(name = "姓名")
    private String name;

    @Schema(name = "状态", description = "0,1")
    @ApiModelPropertyEnum(UserStateEnum.class)
    private Integer state;

    @Email
    @Schema(name = "邮箱")
    private String email;

    @Schema(name = "注册ip")
    private String regIp;

    @Schema(name = "登录ip")
    private String loginIp;


}
