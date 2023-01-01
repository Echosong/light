package com.kdao.light.common.dto.config;

import com.kdao.light.common.component.IDictionaryObject;
import com.kdao.light.common.dto.SysBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>Title:#{EntityTableName}# </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0319:48</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Data
public class ConfigListDTO extends SysBaseDTO implements IDictionaryObject {
    @NotBlank
    @ApiModelProperty(value = "配置字段名", notes = "")
    private String key;

    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "配置说明", notes = "")
    private String name;

    @Range(min = 0)
    @ApiModelProperty(value = "配置分组", notes = "")
    private Integer group;

    @ApiModelProperty(value = "配置值", notes = "")
    private String value;

    @Range(min = 0, max = 3, message = "必须大于")
    @ApiModelProperty(value = "类型", notes = "配置html展现类型")
    private Integer type;

    @Pattern(message = "必须大于2", regexp = "\\s{2,}")
    @ApiModelProperty(value = "描述", notes = "如果选择框用,隔开")
    private String description;


}
