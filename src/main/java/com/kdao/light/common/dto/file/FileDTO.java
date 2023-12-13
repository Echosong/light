package com.kdao.light.common.dto.file;

import com.kdao.light.common.annotation.ApiModelPropertyEnum;
import com.kdao.light.common.component.IDictionaryObject;
import com.kdao.light.common.dto.SysBaseDTO;
import com.kdao.light.common.enums.FileTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0319:48</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Data
public class FileDTO extends SysBaseDTO implements IDictionaryObject {
    @Schema(name = "文件名")
    @NotNull
    private String fileName;

    @Schema(defaultValue = "文件扩展名")
    private String extend;

    @Schema(defaultValue = "文件存储路径")
    private String filePath;

   @Schema(defaultValue = "文件业务类型")
    @ApiModelPropertyEnum(value = FileTypeEnum.class)
    private Integer fileType;

   @Schema(defaultValue = "文件大小")
    private long fileSize;

   @Schema(defaultValue = "相对路径")
    private String urlPath;
}
