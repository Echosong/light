package com.kdao.light.common.dto.user;

import com.kdao.light.common.dto.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002720:03</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Data
public class UserQueryDTO extends PageInfo {
   @Schema(defaultValue = "账号直接用手机号")
    @NotNull
    @Size(min = 10, max = 12, message = "手机格式不对")
    private String username;

   @Schema(defaultValue = "姓名")
    private String name;

   @Schema(defaultValue = "状态默认开启", allowableValues = "0,1" )
    private Integer state = 0 ;

   @Schema(defaultValue = "开始时间")
    private LocalDate startDate ;

   @Schema(defaultValue = "结束时间")
    private LocalDate endDate;
}
