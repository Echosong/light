package #{SYS_PATH}.packet.dto.#{EntityName}#;

import #{SYS_PATH}.common.annotation.ApiModelPropertyEnum;
import #{SYS_PATH}.common.component.IDictionaryObject;
import #{SYS_PATH}.common.dto.PageInfo;
import #{SYS_PATH}.common.dto.SysBaseDTO;
import #{SYS_PATH}.packet.enums.system.*;
import #{SYS_PATH}.packet.enums.business.*;
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
 * #{tableInfo}# pojo转化
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class #{UpEntityName}#DTO extends SysBaseDTO implements IDictionaryObject {
#{fields}#
}
