package cn.light.packet.dto.file;

import cn.light.common.dto.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.light.common.annotation.ApiModelPropertyEnum;
import lombok.Data;

import jakarta.validation.constraints.*;
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
public class FileQueryDTO extends PageInfo {
    @NotNull(message="{jakarta.validation.constraints.NotNull.message}")
   @Schema(title="文件名" )
    private String fileName;

    @Schema(title="文件类型" )
    @ApiModelPropertyEnum(FileTypeEnum.class)
    private Integer fileType;


}
