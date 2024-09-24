package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.anno.NotinListDTO;
import cn.light.common.enums.HtmlTypeEnum;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 商品评价表
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "商品评价")
public class SdProductComment extends SysBase{

    @AutoEntityField(value = "会员id")
    @InQueryDTO
    private Integer memberId;


    @AutoEntityField(value = "父级评价")
    private Integer parentComment;


    @AutoEntityField(value = "评价1-5星")
    @Min(value = 0, message = "评价1-5星不能小于0")
    @Max(value = 5, message = "评价1-5星不能大于5")
    private Integer score;


    @AutoEntityField(value = "商品id")
    private Integer goodId;


    @AutoEntityField(value = "订单id")
    private Integer orderId;


    @AutoEntityField(value = "图片集")
    private String pics;

    @AutoEntityField(value = "评价内容",htmlType = HtmlTypeEnum.TEXTAREA)
    @NotinListDTO
    private String content;


    @AutoEntityField(value = "回复",htmlType = HtmlTypeEnum.TEXTAREA)
    @NotinListDTO
    private String reply;

}
