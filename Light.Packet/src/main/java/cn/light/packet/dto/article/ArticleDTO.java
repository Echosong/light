package cn.light.packet.dto.article;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.packet.dto.SysBaseDTO;
import cn.light.packet.enums.ArticleTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>Title: 新闻</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 19:23:20</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleDTO extends SysBaseDTO implements IDictionaryObject {
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
