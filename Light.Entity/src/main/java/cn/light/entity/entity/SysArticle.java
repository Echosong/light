package cn.light.entity.entity;


import cn.light.common.anno.*;
import cn.light.common.enums.CodeTypeEnum;
import cn.light.common.enums.HtmlTypeEnum;
import cn.light.packet.enums.ArticleTypeEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 注意 类型一定要用应用类型，因为允许为null 会带来一些好处
 * @author : echosong
 * @version :1.0.0
 */

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "新闻")
@AutoCover(value = {CodeTypeEnum.NULL})
public class SysArticle extends SysBase {
    @AutoEntityField(value = "标题")
    @InQueryDTO
    private String title;

    @AutoEntityField(value = "封面图片", htmlType = HtmlTypeEnum.UPLOAD)
    @NotinListDTO
    private String imgUrl;

    @AutoEntityField(value = "类型", enums = ArticleTypeEnum.class)
    @InQueryDTO
    private Integer type;

    @AutoEntityField(value = "内容", htmlType = HtmlTypeEnum.TEXTEDIT)
    private String content;

    @AutoEntityField(value = "附件", htmlType = HtmlTypeEnum.FILE)
    private String fileUrl;

    @AutoEntityField(value = "项目id")
    private  Integer projectId;


}
