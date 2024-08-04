package cn.light.packet.dto.messageTemplate;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.PageInfo;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.*;

import cn.light.packet.enums.system.TemplateTypeEnum;

/**
 * MessageTemplate列表传输对象
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-08-04 22:49:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageTemplateListDTO extends SysBaseDTO implements IDictionaryObject {
   @Schema(title="模板类型" , description="TemplateTypeEnum")
    @ApiModelPropertyEnum(TemplateTypeEnum.class)
    private Integer type;

    @Length(min=0,max=100,message="模板编码长度不能超过100")
   @Schema(title="模板编码" )
    private String code;

   @Schema(title="限定参数" )
    private String params;

   @Schema(title="模板说明" )
    private String title;

   @Schema(title="模板内容" )
    private String content;


}
