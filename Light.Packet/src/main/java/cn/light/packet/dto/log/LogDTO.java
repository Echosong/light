package cn.light.packet.dto.log;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cn.light.common.enums.BusinessEnum;
/**
 * <p>Title: 日志</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 19:23:20</p >
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
    @ApiModelPropertyEnum(BusinessEnum.class)
    private Integer logType;

   @Schema(name="异常详情" )
    private String exceptionDetail;


}
