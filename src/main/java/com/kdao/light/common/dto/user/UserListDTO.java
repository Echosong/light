package com.kdao.light.common.dto.user;

import com.kdao.light.common.component.IDictionaryObject;
import com.kdao.light.common.dto.SysBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * <p>Title:#{EntityTableName}# </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0319:48</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Data
public class UserListDTO extends SysBaseDTO implements IDictionaryObject {
    @NotNull
    @Pattern(message="必须为手机格式",regexp="\\d{11}")
    @ApiModelProperty(value="账号", notes="")
    private String username;

    @Length(min=6,max=2147483647,message="密码必须大于等于6位")
    @ApiModelProperty(value="密码", notes="")
    private String password;

    @Range(min=0,max=1)
    @ApiModelProperty(value="性别", notes="")
    private Integer sex;

    @Length(min=2,max=2147483647,message="姓名必须大于两个字符")
    @ApiModelProperty(value="姓名", notes="")
    private String name;

    @ApiModelProperty(value="状态默认开启", notes="0,1")
    private Integer state;

    @Email
    @ApiModelProperty(value="邮箱", notes="")
    private String email;

    @ApiModelProperty(value="注册ip", notes="")
    private String regIp;

    @ApiModelProperty(value="登录ip", notes="")
    private String loginIp;


}
