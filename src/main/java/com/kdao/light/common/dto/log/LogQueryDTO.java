package com.kdao.light.common.dto.log;

import com.kdao.light.common.dto.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * <p>Title:#{EntityTableName}# </p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2021-11-10 22:35:56</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Data
public class LogQueryDTO extends PageInfo {
    @Schema(defaultValue = "描述")
    private String description;

    @Schema(defaultValue = "创建时间")
    private Date startCreateTime;

    @Schema(defaultValue = "创建时间")
    private Date endCreateTime;

    private Integer logType;


}
