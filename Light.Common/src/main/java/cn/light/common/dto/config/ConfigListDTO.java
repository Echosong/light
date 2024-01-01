package cn.light.common.dto.config;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.PageInfo;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.*;

import cn.light.common.enums.HtmlTypeEnum;

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
public class ConfigListDTO extends SysBaseDTO implements IDictionaryObject {
    @NotBlank(message="{jakarta.validation.constraints.NotBlank.message}")
   @Schema(name="配置字段名" )
    private String key;

    @Email(message="{jakarta.validation.constraints.Email.message}")
    @Length(min=6,max=16,message="长度必须为6-16")
   @Schema(name="配置说明" )
    private String name;

    @Range(min=0)
   @Schema(name="配置分组" )
    private Integer group;

   @Schema(name="配置值" )
    private String value;

    @Range(min=0,max=10,message="必须大于")
   @Schema(name="类型" , description="配置html展现类型")
    @ApiModelPropertyEnum(HtmlTypeEnum.class)
    private Integer type;

    @Pattern(message="必须大于2",regexp="\\s{2,}")
   @Schema(name="描述" , description="如果选择框用,隔开")
    private String description;


}
