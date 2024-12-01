package cn.light.packet.dto.config;

import cn.light.common.dto.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>Title:系统配置 </p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 19:23:19</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConfigQueryDTO extends PageInfo {
    @NotBlank
    @Schema(title = "配置字段名")
    private String key;


    @Schema(title = "配置说明")
    private String name;


}
