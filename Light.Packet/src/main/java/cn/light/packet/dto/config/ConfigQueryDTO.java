package cn.light.packet.dto.config;

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
 * <p>Title:系统配置 </p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 19:23:19</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConfigQueryDTO extends PageInfo {
    @NotBlank(message="{jakarta.validation.constraints.NotBlank.message}")
   @Schema(name="配置字段名" )
    private String key;

    @Email(message="{jakarta.validation.constraints.Email.message}")
    @Length(min=6,max=16,message="长度必须为6-16")
   @Schema(name="配置说明" )
    private String name;


}
