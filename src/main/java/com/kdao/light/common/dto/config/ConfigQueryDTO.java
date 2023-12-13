package com.kdao.light.common.dto.config;

import com.kdao.light.common.dto.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
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
   @Schema(defaultValue="配置字段名")
    private String key;

    @NotBlank(message="不能为空")
   @Schema(defaultValue="配置说明")
    private String name;


}
