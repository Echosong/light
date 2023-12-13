package com.kdao.light.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(defaultValue = "人脸对比上传信息")
public class CompareInfo {
   @Schema(defaultValue = "base64特征信息")
    private String feature;

   @Schema(defaultValue = "项目id")
    private Integer projectId;
}
