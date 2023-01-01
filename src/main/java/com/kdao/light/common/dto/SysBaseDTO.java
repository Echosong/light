package com.kdao.light.common.dto;

import com.kdao.light.common.annotation.ExcelAnnotation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "底层DTO")
public class SysBaseDTO {
    @ExcelAnnotation(ignore = true)
    @ApiModelProperty("主键")
    protected Integer id;
    @ExcelAnnotation(ignore = true)
    @ApiModelProperty(value = "创建时间", readOnly = true)
    protected Date createTime;
    @ExcelAnnotation(ignore = true)
    @ApiModelProperty(value = "更新时间", readOnly = true)
    protected Date updateTime;
    @ExcelAnnotation(ignore = true)
    @ApiModelProperty(value = "创建用户Id", readOnly = true)
    protected Integer creatorId;
    @ExcelAnnotation(ignore = true)
    @ApiModelProperty(value = "更新用户Id", readOnly = true)
    protected Integer updaterId;
}