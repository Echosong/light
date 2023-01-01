package com.kdao.light.common.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("人脸对比上传信息")
public class CompareInfo {
    @ApiModelProperty("base64特征信息")
    private String feature;

    @ApiModelProperty("项目id")
    private Integer projectId;
}
