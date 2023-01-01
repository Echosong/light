package com.kdao.light.common.dto.config;

import com.kdao.light.common.dto.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>Title:#{EntityTableName}# </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/29 002922:24</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Data
public class ConfigQueryDTO extends PageInfo {
    @NotBlank
    @ApiModelProperty(value="配置字段名", notes="")
    private String key;

    @NotBlank(message="不能为空")
    @ApiModelProperty(value="配置说明", notes="")
    private String name;


}
