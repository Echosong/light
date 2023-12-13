package com.kdao.light.common.dto.role;

import com.kdao.light.common.component.IDictionaryObject;
import com.kdao.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>Title: #{EntityTableName}#</p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0319:48</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Data
public class RoleDTO extends SysBaseDTO implements IDictionaryObject {
    @NotNull
   @Schema(defaultValue = "角色名称")
    private String name;

   @Schema(defaultValue = "描述")
    private String description;
}
