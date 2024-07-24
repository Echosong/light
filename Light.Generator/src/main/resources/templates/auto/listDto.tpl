package #{SYS_PATH}.packet.dto.#{EntityName}#;

import #{SYS_PATH}.common.annotation.ApiModelPropertyEnum;
import #{SYS_PATH}.common.component.IDictionaryObject;
import #{SYS_PATH}.common.dto.PageInfo;
import #{SYS_PATH}.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.*;

#{import}#

/**
 * #{UpEntityName}#列表传输对象
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : #{localDate}#
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class #{UpEntityName}#ListDTO extends SysBaseDTO implements IDictionaryObject {
#{fields}#
}
