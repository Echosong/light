package com.kdao.light.common.dto.permission;

import com.kdao.light.common.dto.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/28 002815:12</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Data
public class PermissionQueryDTO extends PageInfo {
   @Schema(defaultValue = "权限名称")
    private String name = "";
   @Schema(defaultValue = "权限编码")
    private String perms = "";
   @Schema(defaultValue = "权限类型 0：目录   1：菜单   2：按钮",allowableValues = "0,1,2")
    private Integer type = 1;
}
