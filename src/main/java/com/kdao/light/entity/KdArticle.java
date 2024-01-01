package com.kdao.light.entity;

import com.kdao.autocode.anno.AutoCover;
import com.kdao.autocode.anno.AutoEntity;
import com.kdao.autocode.anno.AutoEntityField;
import com.kdao.autocode.anno.InQueryDTO;
import com.kdao.autocode.enums.CodeTypeEnum;
import com.kdao.light.common.enums.ArticleTypeEnum;
import com.kdao.light.common.enums.HtmlTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;

/**
 * <p>Title: LightController</p >
 * <p>Description: KdArticle</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2022-09-21 12:18 </p >
 *
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
public class KdArticle extends SysBase {
    @AutoEntityField(value = "标题")
    @InQueryDTO
    private String title;

    @AutoEntityField(value = "封面图片", htmlType = HtmlTypeEnum.UPLOAD)
    private String imgUrl;

    @AutoEntityField(value = "类型", enums = ArticleTypeEnum.class)
    @InQueryDTO
    private int type;

    @AutoEntityField(value = "内容", htmlType = HtmlTypeEnum.TEXTEDIT)
    private String content;

    @AutoEntityField(value = "附件", htmlType = HtmlTypeEnum.FILE)
    private String fileUrl;

    @AutoEntityField(value = "项目id")
    private  Integer projectId;


}
