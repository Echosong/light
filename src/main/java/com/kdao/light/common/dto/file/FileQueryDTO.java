package com.kdao.light.common.dto.file;

import com.kdao.light.common.dto.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/29 002922:24</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Data
public class FileQueryDTO extends PageInfo {
    @ApiModelProperty("业务类型")
    private Integer fileType;

    @ApiModelProperty("文件名")
    private String fileName;
}
