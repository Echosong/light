package cn.light.packet.dto.messageTemplate;

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
* MessageTemplateQueryDTO 查询DTO
* email:zq_songfeigang@163.com
*
* @author : 二胡子
* @version : 1.0
* @date : 2024-08-04 22:49:58
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageTemplateQueryDTO extends PageInfo {
    @Length(min=0,max=100,message="模板编码长度不能超过100")
   @Schema(title="模板编码" )
    private String code;


}
