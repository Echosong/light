package com.kdao.light.common.dto.log;

import com.kdao.light.common.dto.PageInfo;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value="描述", notes="")
    private String description;

    @ApiModelProperty(value="创建时间", notes="")
    private Date startCreateTime;

    @ApiModelProperty(value="创建时间", notes="")
    private Date endCreateTime;


}
