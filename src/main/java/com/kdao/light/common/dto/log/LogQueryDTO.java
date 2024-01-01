package com.kdao.light.common.dto.log;

import com.kdao.light.common.dto.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import com.kdao.light.common.annotation.ApiModelPropertyEnum;
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
import com.kdao.light.common.enums.BusinessEnum;
/**
 * <p>Title:日志 </p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 10:01:08</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LogQueryDTO extends PageInfo {
   @Schema(name="用户" )
    private String username;

   @Schema(name="描述" )
    private String description;

   @Schema(name="日志类型" )
    @ApiModelPropertyEnum(BusinessEnum.class)
    private Integer logType;


}
