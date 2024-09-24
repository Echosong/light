package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.enums.HtmlTypeEnum;
import cn.light.packet.enums.business.MemberPointTypeEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author : 二胡子
 * @version :1.0.0
 * @Description: 用户积分记录表
 */

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "用户积分记录表")
public class SdMemberPointLog extends SysBase {
    @AutoEntityField(value = "用户id")
    @InQueryDTO
    private Integer memberId;

    @AutoEntityField(value = "用户账号")
    @InQueryDTO
    private String memberName;

    @AutoEntityField(value = "标题")
    @InQueryDTO
    private String title;

    @AutoEntityField(value = "积分变动类型", enums = MemberPointTypeEnum.class)
    @InQueryDTO
    private Integer type;

    @AutoEntityField(value = "积分数量")
    private Integer num;
    @AutoEntityField(value = "积分余额")
    private Integer balance;

    @AutoEntityField(value = "备注", htmlType = HtmlTypeEnum.TEXTAREA)
    private String remark;


    @AutoEntityField(value = "等级类型")
    private Integer levelType;

}
