package com.kdao.light.common.dto;

import com.kdao.light.common.annotation.ExcelAnnotation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "底层DTO")
public class SysBaseDTO {
    @ExcelAnnotation(ignore = true)
   @Schema(defaultValue = "主键")
    protected Integer id;
    @ExcelAnnotation(ignore = true)
   @Schema(defaultValue = "创建时间", readOnly = true)
    protected Date createTime;
    @ExcelAnnotation(ignore = true)
   @Schema(defaultValue = "更新时间", readOnly = true)
    protected Date updateTime;
    @ExcelAnnotation(ignore = true)
   @Schema(defaultValue = "创建用户Id", readOnly = true)
    protected Integer creatorId;
    @ExcelAnnotation(ignore = true)
   @Schema(defaultValue = "更新用户Id", readOnly = true)
    protected Integer updaterId;
}