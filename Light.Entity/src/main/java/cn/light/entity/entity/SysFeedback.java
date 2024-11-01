package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.anno.NotinListDTO;
import cn.light.common.enums.HtmlTypeEnum;
import cn.light.packet.enums.business.FeedbackCategoryEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 意见反馈实体类
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "意见反馈")
//@AutoCover(value = {CodeTypeEnum.LIST_VIEW, CodeTypeEnum.QUERY_DTO})
public class SysFeedback extends SysBase {

    @AutoEntityField(value = "用户id")
    @NotinListDTO
    private Integer userId;

    @AutoEntityField(value = "用户账号")
    @InQueryDTO
    private String userAccount;

    @AutoEntityField(value = "图片", htmlType = HtmlTypeEnum.UPLOAD)
    private String images;

    @AutoEntityField(value = "内容")
    private String content;

    @AutoEntityField(value = "联系方式")
    private String contact;

    @AutoEntityField(value = "是否已读")
    private Short isRead;

    //分类
    @AutoEntityField(value = "意见反馈问题分类", enums = FeedbackCategoryEnum.class)
    private Integer category;
}
