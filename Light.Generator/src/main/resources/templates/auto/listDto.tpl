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
 * <p>Title:#{tableInfo}# </p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: #{localDate}#</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class #{UpEntityName}#ListDTO extends SysBaseDTO implements IDictionaryObject {
#{fields}#
}
