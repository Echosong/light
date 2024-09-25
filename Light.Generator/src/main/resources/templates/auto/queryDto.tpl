package #{SYS_PATH}.packet.dto.#{EntityName}#;

import #{SYS_PATH}.packet.enums.system.*;
import #{SYS_PATH}.packet.enums.business.*;
import #{SYS_PATH}.common.dto.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import #{SYS_PATH}.common.annotation.ApiModelPropertyEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.*;
#{import}#
/**
* #{UpEntityName}#QueryDTO 查询DTO
* email:zq_songfeigang@163.com
*
* @author : 二胡子
* @version : 1.0
* @date : #{localDate}#
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class #{UpEntityName}#QueryDTO extends PageInfo {
#{fields}#
}
