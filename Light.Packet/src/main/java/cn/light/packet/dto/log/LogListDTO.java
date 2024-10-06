package cn.light.packet.dto.log;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.SysBaseDTO;
import cn.light.common.enums.BusinessEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;



/**
 * Log列表传输对象
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-10-06 12:17:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LogListDTO extends SysBaseDTO implements IDictionaryObject {
    @Schema(title = "用户")
    private String username;

    @Schema(title = "日志类型", description = "BusinessEnum")
    @ApiModelPropertyEnum(BusinessEnum.class)
    private Integer logType;

    @Schema(title = "请求ip")
    private String requestIp;

    @Schema(title = "地址")
    private String address;

    @Schema(title = "描述")
    private String description;

    @Schema(title = "浏览器")
    private String browser;

    @Schema(title = "请求耗时")
    private Long time;

    @Schema(title = "方法名")
    private String method;

    @Schema(title = "参数")
    private String params;

    @Schema(title = "请求路径")
    private String urlPath;


}
