package com.kdao.light.common.dto.article;

import com.kdao.light.common.component.IDictionaryObject;
import com.kdao.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Title: 文章</p >
 * <p>Description: </p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2022-09-21 15:32:32</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleDTO extends SysBaseDTO implements IDictionaryObject {
   @Schema(defaultValue="标题" )
    private String title;

   @Schema(defaultValue="类型" )
    private String type;

   @Schema(defaultValue="内容" )
    private String content;

   @Schema(defaultValue="项目id" )
    private Integer projectId;


}
