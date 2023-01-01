package com.kdao.light.common.dto.user;

import io.swagger.annotations.ApiModelProperty;

public class RegUserDTO extends UserDTO {
    @ApiModelProperty("base64头像信息")
    private String photo;
}
