package com.kdao.light.common.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0823:19</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Data
public class UserKeyDTO {
    private String username;
   @Schema(defaultValue = "用户名")
    private String name;
   @Schema(defaultValue = "用户id")
    private Integer id;
}
