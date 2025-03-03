package cn.light.packet.dto.user;

import cn.light.packet.dto.permission.PermissionDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 登录后返回
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
public class LoginResultDTO extends UserDTO{
    @Schema(defaultValue = "token")
    private String token;

    private List<PermissionDTO> menuList;

}
