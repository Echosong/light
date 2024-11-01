package cn.light.packet.dto.log;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.SysBaseDTO;
import cn.light.common.enums.BusinessEnum;
import cn.light.packet.enums.system.YesOrNoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日志 pojo转化
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LogDTO extends SysBaseDTO implements IDictionaryObject {
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

    @Schema(title = "客户端")
    private String browser;

    @Schema(title = "请求耗时")
    private Long time;

    @Schema(title = "方法名")
    private String method;

    @Schema(title = "参数")
    private String params;

    @Schema(title = "请求路径")
    private String urlPath;

    @Schema(title = "状态", description = "YesOrNoEnum0,1")
    @ApiModelPropertyEnum(YesOrNoEnum.class)
    private Integer state;

    @Schema(title = "异常详情")
    private String exceptionDetail;


}
