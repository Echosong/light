package cn.light.packet.dto.user;

import cn.light.common.dto.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.light.common.annotation.ApiModelPropertyEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.*;

/**
 * 用户查询条件
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryDTO extends PageInfo {
    @NotNull(message = "{jakarta.validation.constraints.NotNull.message}")
    @Pattern(message = "必须为手机格式", regexp = "\\d{11}")
    @Schema(title = "账号")
    private String username;

    @Length(min = 2, max = 2147483647, message = "姓名必须大于两个字符")
    @Schema(title = "姓名")
    private String name;

    @Schema(title = "关键字")
    private String keywords;

    //角色
    @Schema(title = "角色")
    private Integer roleId;


}
