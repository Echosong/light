package com.kdao.light.common.dto.file;

import com.kdao.light.common.component.IDictionaryObject;
import com.kdao.light.common.dto.SysBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * <p>Title:#{EntityTableName}# </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0319:48</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FileListDTO extends SysBaseDTO implements IDictionaryObject {
    @NotNull
    @ApiModelProperty(value="文件名")
    private String fileName;

    @ApiModelProperty(value="文件扩展名")
    private String extend;

    @ApiModelProperty(value="文件存储路径")
    private String filePath;

    @ApiModelProperty(value="文件业务类型")
    private Integer fileType;

    @ApiModelProperty(value="文件大小")
    private Long fileSize;

    @ApiModelProperty(value="相对路径")
    private String urlPath;


}
