package com.kdao.light.common.dto.log;

import com.kdao.light.common.component.IDictionaryObject;
import com.kdao.light.common.dto.SysBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>Title: #{EntityTableName}#</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2021-11-10 21:51:47</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Data
public class LogDTO extends SysBaseDTO implements IDictionaryObject {
    @ApiModelProperty(value="请求ip", notes="")
    private String requestIp;

    @ApiModelProperty(value="地址", notes="")
    private String address;

    @ApiModelProperty(value="描述", notes="")
    private String description;

    @ApiModelProperty(value="浏览器", notes="")
    private String browser;

    @ApiModelProperty(value="请求耗时", notes="")
    private Long time;

    @ApiModelProperty(value="方法名", notes="")
    private String method;

    @ApiModelProperty(value="参数", notes="")
    private String params;

    @ApiModelProperty(value="日志类型", notes="")
    private String logType;

    @ApiModelProperty(value="异常详情", notes="")
    private String exceptionDetail;


}
