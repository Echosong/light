package com.kdao.light.common.dto.log;

import com.kdao.light.common.annotation.ApiModelPropertyEnum;
import com.kdao.light.common.component.IDictionaryObject;
import com.kdao.light.common.dto.PageInfo;
import com.kdao.light.common.dto.SysBaseDTO;
import com.kdao.light.common.enums.FileTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
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

/**
 * <p>Title: 日志</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2023-12-31 14:04:19</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LogDTO extends SysBaseDTO implements IDictionaryObject {
   @Schema(name="用户" )
    private String username;

   @Schema(name="请求ip" )
    private String requestIp;

   @Schema(name="地址" )
    private String address;

   @Schema(name="描述" )
    private String description;

   @Schema(name="浏览器" )
    private String browser;

   @Schema(name="请求耗时" )
    private Long time;

   @Schema(name="方法名" )
    private String method;

   @Schema(name="参数" )
    private String params;

   @Schema(name="日志类型" )
    private String logType;

   @Schema(name="异常详情" )
    private String exceptionDetail;


}
