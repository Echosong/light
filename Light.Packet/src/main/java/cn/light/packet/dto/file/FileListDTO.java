package cn.light.packet.dto.file;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;

import cn.light.packet.enums.system.FileTypeEnum;

/**
 * <p>Title:文件管理 </p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 19:23:19</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FileListDTO extends SysBaseDTO implements IDictionaryObject {
   @Schema(title="图标" )
    private String ico;

    @NotNull(message="{jakarta.validation.constraints.NotNull.message}")
   @Schema(title="文件名" )
    private String fileName;

   @Schema(title="文件扩展名" )
    private String extend;

   @Schema(title="文件存储路径" )
    private String filePath;

   @Schema(title="文件类型" )
    @ApiModelPropertyEnum(FileTypeEnum.class)
    private Integer fileType;

   @Schema(title="文件大小" )
    private long fileSize;

   @Schema(title="相对路径" )
    private String urlPath;

   @Schema(title="文件唯一标识" )
    private String uuid;


}
