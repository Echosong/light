package cn.light.packet.dto.log;

import cn.light.common.dto.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.light.common.annotation.ApiModelPropertyEnum;
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
public class LogQueryDTO extends PageInfo {
    @Schema(title = "用户")
    private String username;

    @Schema(title = "描述")
    private String description;

    @Schema(title = "日志类型")
    @ApiModelPropertyEnum(BusinessEnum.class)
    private Integer logType;

    @Schema(title = "开始时间")
    private String startDate;

    @Schema(title = "结束时间")
    private String endDate;

    @Schema(title = "状态")
    private Integer state;


}
