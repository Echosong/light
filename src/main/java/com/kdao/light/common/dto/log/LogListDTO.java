package com.kdao.light.common.dto.log;

import com.kdao.light.common.component.IDictionaryObject;
import com.kdao.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * <p>Title:#{EntityTableName}# </p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2021-11-10 21:51:47</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Data
public class LogListDTO extends SysBaseDTO implements IDictionaryObject {
   @Schema(defaultValue="请求ip")
    private String requestIp;

   @Schema(defaultValue="地址")
    private String address;

   @Schema(defaultValue="描述")
    private String description;

   @Schema(defaultValue="浏览器")
    private String browser;

   @Schema(defaultValue="请求耗时")
    private Long time;

   @Schema(defaultValue="方法名")
    private String method;

   @Schema(defaultValue="参数")
    private String params;

   @Schema(defaultValue="日志类型")
    private String logType;

   @Schema(defaultValue="异常详情")
    private byte[] exceptionDetail;


}
