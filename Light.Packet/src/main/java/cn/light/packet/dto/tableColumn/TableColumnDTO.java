package cn.light.packet.dto.tableColumn;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.PageInfo;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.*;

/**
 * 存储列设置表 pojo转化
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TableColumnDTO extends SysBaseDTO implements IDictionaryObject {
   @Schema(title="用户id" )
    private Long userId;

   @Schema(title="表名" )
    private String tableName;

   @Schema(title="表列" )
    private String columns;


}
