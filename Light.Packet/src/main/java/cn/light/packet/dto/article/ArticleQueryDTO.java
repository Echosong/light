package cn.light.packet.dto.article;

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
import cn.light.packet.enums.ArticleTypeEnum;
/**
* ArticleQueryDTO 查询DTO
* email:zq_songfeigang@163.com
*
* @author : 二胡子
* @version : 1.0
* @date : 2024-08-04 14:26:48
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleQueryDTO extends PageInfo {
   @Schema(title="标题" )
    private String title;

   @Schema(title="类型" , description="ArticleTypeEnum")
    @ApiModelPropertyEnum(ArticleTypeEnum.class)
    private Integer type;

   @Schema(title="发布时间" )
    private Date startPublishTime;

   @Schema(title="发布时间" )
    private Date endPublishTime;


}
