package cn.light.packet.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/28 002810:22</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Data
public class LoginUserDTO {

    @Schema(defaultValue = "账号")
    @Pattern(regexp = "\\d{11}", message = "必须为手机格式")
    private String username;

    @Size(min = 6, message = "密码必须大于等于6位")
    @Schema(defaultValue = "密码")
    private String password;

    @Size(min = 3, message = "请输入验证")
    @Schema(defaultValue = "验证码")
    private String code;

    @Schema(defaultValue = "验证码Uid")
    private String codeUid;

    @Schema(defaultValue = "登录客户端ip")
    private String loginIp;
}
