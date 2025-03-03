package cn.light.packet.dto.dictType;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cn.light.packet.enums.system.YesOrNoEnum;
/**
 * <p>Title: 字典类型</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-07 23:00:48</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DictTypeDTO extends SysBaseDTO implements IDictionaryObject {
   @Schema(title="字典名称" )
    private String dictName;

   @Schema(title="字典类型" )
    private String dictType;

   @Schema(title="状态" , description="1=正常,0=停用")
    @ApiModelPropertyEnum(YesOrNoEnum.class)
    private Integer state;


}
