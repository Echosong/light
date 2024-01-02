package cn.light.packet.dto.user;

import cn.light.packet.dto.PageInfo;
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
 * <p>Title:用户 </p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 19:23:19</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryDTO extends PageInfo {
    @NotNull(message="{jakarta.validation.constraints.NotNull.message}")
    @Pattern(message="必须为手机格式",regexp="\\d{11}")
   @Schema(name="账号" )
    private String username;

    @Length(min=2,max=2147483647,message="姓名必须大于两个字符")
   @Schema(name="姓名" )
    private String name;


}
