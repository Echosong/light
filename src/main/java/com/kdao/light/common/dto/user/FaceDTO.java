package com.kdao.light.common.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>Title: LightController</p >
 * <p>Description: FaceDTO</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2022-09-28 15:04 </p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@ApiModel("人脸对比上传信息")
@Data
public class FaceDTO {
    @ApiModelProperty("人脸图片 base64")
    private String image;

    @ApiModelProperty("项目编码")
    private String app;
}
