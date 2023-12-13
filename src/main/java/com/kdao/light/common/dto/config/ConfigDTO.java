package com.kdao.light.common.dto.config;

import com.kdao.light.common.component.IDictionaryObject;
import com.kdao.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

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
public class ConfigDTO extends SysBaseDTO implements IDictionaryObject {

   @Schema(defaultValue="配置字段名")
    private String key;

    @NotBlank
   @Schema(defaultValue="配置说明")
    private String name;


   @Schema(defaultValue="配置分组")
    private Integer group;

   @Schema(defaultValue="配置值")
    private String value;


   @Schema(defaultValue="类型", description="配置html展现类型")

    private Integer type;

   @Schema(defaultValue="描述", description="如果选择框用,隔开")
    private String description;


}
