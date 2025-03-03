package cn.light.packet.dto.config;

import cn.hutool.json.JSONArray;
import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.PageInfo;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.*;

import cn.light.common.enums.HtmlTypeEnum;

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
public class ConfigListDTO extends SysBaseDTO implements IDictionaryObject {
    @NotBlank(message = "{jakarta.validation.constraints.NotBlank.message}")
    @Schema(title = "配置字段名")
    private String key;

    @Email(message = "{jakarta.validation.constraints.Email.message}")
    @Schema(title = "配置说明")
    private String name;


    @Schema(title = "配置分组")
    private Integer group;

    @Schema(title = "配置值")
    private String value;


    @Schema(title = "类型", description = "配置html展现类型")
    @ApiModelPropertyEnum(HtmlTypeEnum.class)
    private Integer type;

    @Pattern(message = "必须大于2", regexp = "\\s{2,}")
    @Schema(title = "描述", description = "如果选择框用,隔开")
    private String description;

    @Schema(title = "选项")
    private JSONArray options;


}
