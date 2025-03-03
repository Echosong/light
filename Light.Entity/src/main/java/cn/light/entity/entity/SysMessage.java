package cn.light.entity.entity;

import cn.light.common.anno.AutoCover;
import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.enums.CodeTypeEnum;
import cn.light.common.enums.HtmlTypeEnum;
import cn.light.packet.enums.system.MessageStateEnum;
import cn.light.packet.enums.system.TemplateTypeEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * SysMessage
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 * @date : 2024/8/4 22:35
 */

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "站内消息、短息 记录")
//@AutoCover(value = {CodeTypeEnum.MAPPER_XML, CodeTypeEnum.QUERY_DTO, CodeTypeEnum.LIST_DTO, CodeTypeEnum.LIST_VIEW})
public class SysMessage  extends  SysBase{

    @AutoEntityField(value = "消息类型", enums = TemplateTypeEnum.class)
    @InQueryDTO
    private Integer type;

    @AutoEntityField(value = "消息模板编码")
    @InQueryDTO
    private String templateCode;

    @AutoEntityField(value = "消息接收者")
    @InQueryDTO
    private Integer receiverId;

    //接受者账号
    @AutoEntityField(value = "消息接收者账号")
    @InQueryDTO
    private String receiverAccount;

    @AutoEntityField(value = "消息发送者")
    private Integer senderId;

    @AutoEntityField(value = "消息发送者账号")
    private String senderAccount;

    //状态
    @AutoEntityField(value = "状态", enums = MessageStateEnum.class)
    private Integer state;

    @AutoEntityField(value = "消息参数")
    private String params;

    //消息内容
    @AutoEntityField(value = "消息内容", htmlType = HtmlTypeEnum.TEXTAREA)
    private String message;

}
