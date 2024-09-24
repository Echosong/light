package cn.light.entity.entity;

import cn.light.common.anno.*;
import cn.light.common.enums.HtmlTypeEnum;
import cn.light.packet.enums.PartnerAuditStateEnum;
import cn.light.packet.enums.PostTypeEnum;
import cn.light.packet.enums.business.AuditStateEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 帖子
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "用户发帖")
//@AutoCover(value = {CodeTypeEnum.ALL })
public class SdPost extends SysBase{
    /** 用户账户(跟accout一样) */
    @AutoEntityField(value = "发帖人", notes = "用户账户(跟accout一样)")
    @InQueryDTO
    private String memberName;

    @AutoEntityField(value = "会员id", notes = "会员id")
    private Integer memberId;


    @AutoEntityField("标题")
    @InQueryDTO
    private String title;


    @AutoEntityField(value = "内容",  htmlType = HtmlTypeEnum.TEXTAREA)
    private String content;

    @AutoEntityField(value = "动态配图", htmlType = HtmlTypeEnum.UPLOAD)
    private String pics;

    @AutoEntityField("是否显示发布者位置")
    @NotinListDTO
    private String showGeo;

    @AutoEntityField("地址")
    private String address;

    @AutoEntityField("是否匿名")
    private Integer isAnonymous;



    @AutoEntityField(value = "状态",enums = AuditStateEnum.class)
    private Integer status;

    //是否热门
    @AutoEntityField(value = "是否热门",notes = "1 热门，0 不热门")
    @InQueryDTO
    private Integer isHot;
    

    @AutoEntityField(value = "获得的打赏")
    @AutoEntityFieldDefault("0")
    private Integer reward;

    //点赞数
    @AutoEntityField(value = "点赞数")
    @AutoEntityFieldDefault("0")
    private Integer likeNum;

    //评论数
    @Schema(title = "评论数")
    @AutoEntityFieldDefault("0")
    private Integer commentNum;

    //收藏数
    @Schema(title = "收藏数")
    @AutoEntityFieldDefault("0")
    private Integer collectNum;

    //点击量
    @Schema(title = "点击量")
    @AutoEntityFieldDefault("0")
    private Integer clickNum;

}
