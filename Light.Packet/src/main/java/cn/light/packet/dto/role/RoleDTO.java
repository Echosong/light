package cn.light.packet.dto.role;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.PageInfo;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>Title: 所属客户</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 19:23:19</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDTO extends SysBaseDTO implements IDictionaryObject {
    @NotNull
    @Schema(title = "客户名称")
    private String name;

    @Schema(title = "编码")
    private String code;

    @Schema(title = "描述")
    private String description;


}
