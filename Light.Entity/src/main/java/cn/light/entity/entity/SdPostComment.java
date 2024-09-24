package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.enums.HtmlTypeEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 游记评价
 * @author : 二胡子
 * @version :1.0.0
 */

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "游记评价")
public class SdPostComment extends SysBase {
    @AutoEntityField(value = "游记id")
    @InQueryDTO
    private Integer postId;

    @AutoEntityField(value = "会员id")
    @InQueryDTO
    private Integer memberId;

    @AutoEntityField(value = "会员账号")
    private String memberName;

    //头像
    @AutoEntityField("头像")
    private String memberAvatar;


    @AutoEntityField(value = "评论内容", htmlType = HtmlTypeEnum.TEXTAREA)
    private String content;

    @AutoEntityField(value = "回复id")
    private Integer replyId;

    @AutoEntityField(value = "回复对象")
    private String replyMemberName;

    @AutoEntityField(value = "点赞数")
    private Integer goods;

}
