package cn.light.packet.dto.tableColumn;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.PageInfo;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.*;



/**
 * TableColumn列表传输对象
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-07-28 22:43:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TableColumnListDTO extends SysBaseDTO implements IDictionaryObject {
   @Schema(title="用户id" )
    private Long userId;

   @Schema(title="表名" )
    private String tableName;

   @Schema(title="表列" )
    private String columns;


}
