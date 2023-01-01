package #{SYS_PATH}.common.dto.#{EntityName}#;

import #{SYS_PATH}.common.dto.PageInfo;
import io.swagger.annotations.ApiModelProperty;
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
public class #{UpEntityName}#QueryDTO extends PageInfo {
#{fields}#
}
