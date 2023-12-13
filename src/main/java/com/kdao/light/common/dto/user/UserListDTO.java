package com.kdao.light.common.dto.user;

import com.kdao.light.common.component.IDictionaryObject;
import com.kdao.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
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
   @Schema(defaultValue="账号")
    private String username;

    @Length(min=6,max=2147483647,message="密码必须大于等于6位")
   @Schema(defaultValue="密码")
    private String password;

    @Range(min=0,max=1)
   @Schema(defaultValue="性别")
    private Integer sex;

    @Length(min=2,max=2147483647,message="姓名必须大于两个字符")
   @Schema(defaultValue="姓名")
    private String name;

   @Schema(defaultValue="状态默认开启", description="0,1")
    private Integer state;

    @Email
   @Schema(defaultValue="邮箱")
    private String email;

   @Schema(defaultValue="注册ip")
    private String regIp;

   @Schema(defaultValue="登录ip")
    private String loginIp;


}
