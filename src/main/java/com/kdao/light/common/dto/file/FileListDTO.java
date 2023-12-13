package com.kdao.light.common.dto.file;

import com.kdao.light.common.component.IDictionaryObject;
import com.kdao.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
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
   @Schema(defaultValue="文件名")
    private String fileName;

   @Schema(defaultValue="文件扩展名")
    private String extend;

   @Schema(defaultValue="文件存储路径")
    private String filePath;

   @Schema(defaultValue="文件业务类型")
    private Integer fileType;

   @Schema(defaultValue="文件大小")
    private Long fileSize;

   @Schema(defaultValue="相对路径")
    private String urlPath;


}
