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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.*;

import cn.light.packet.enums.ArticleTypeEnum;

/**
 * <p>Title:新闻 </p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-02 16:49:59</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleListDTO extends SysBaseDTO implements IDictionaryObject {
   @Schema(name="标题" )
    private String title;

   @Schema(name="封面图片" )
    private String imgUrl;

   @Schema(name="类型" )
    @ApiModelPropertyEnum(ArticleTypeEnum.class)
    private int type;

   @Schema(name="内容" )
    private String content;

   @Schema(name="附件" )
    private String fileUrl;

   @Schema(name="项目id" )
    private Integer projectId;


}
