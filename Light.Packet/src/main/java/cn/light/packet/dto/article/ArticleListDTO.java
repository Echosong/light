package cn.light.packet.dto.article;

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

import cn.light.packet.enums.ArticleTypeEnum;

/**
 * Article列表传输对象
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-08-04 15:21:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleListDTO extends SysBaseDTO implements IDictionaryObject {
   @Schema(title="标题" )
    private String title;

   @Schema(title="类型" , description="ArticleTypeEnum")
    @ApiModelPropertyEnum(ArticleTypeEnum.class)
    private Integer type;

   @Schema(title="内容" )
    private String content;

   @Schema(title="附件" )
    private String fileUrl;

   @Schema(title="项目id" )
    private Integer projectId;

   @Schema(title="发布时间" )
    private Date publishTime;


}
