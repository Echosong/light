package cn.light.packet.dto.log;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import cn.light.common.enums.BusinessEnum;

/**
 * <p>Title:日志 </p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 19:23:20</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LogListDTO extends SysBaseDTO implements IDictionaryObject {
   @Schema(title="用户" )
    private String username;

   @Schema(title="请求ip" )
    private String requestIp;

   @Schema(title="地址" )
    private String address;

   @Schema(title="描述" )
    private String description;

   @Schema(title="浏览器" )
    private String browser;

   @Schema(title="请求耗时" )
    private Long time;

   @Schema(title="方法名" )
    private String method;

   @Schema(title="参数" )
    private String params;

   @Schema(title="日志类型" )
    @ApiModelPropertyEnum(BusinessEnum.class)
    private Integer logType;


}
