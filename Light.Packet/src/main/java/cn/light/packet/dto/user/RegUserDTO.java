package cn.light.packet.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

public class RegUserDTO extends UserDTO {
   @Schema(defaultValue = "base64头像信息")
    private String photo;
}
