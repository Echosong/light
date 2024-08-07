package cn.light.packet.dto.article;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;
import cn.light.packet.enums.system.ArticleTypeEnum;
/**
 * 新闻 pojo转化
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleDTO extends SysBaseDTO implements IDictionaryObject {
   @Schema(title="标题" )
    private String title;

   @Schema(title="封面图片" )
    private String imgUrl;

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
