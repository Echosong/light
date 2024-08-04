package cn.light.packet.dto.message;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.PageInfo;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.*;

import cn.light.packet.enums.system.TemplateTypeEnum;
import cn.light.packet.enums.system.MessageStateEnum;

/**
 * Message列表传输对象
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-08-04 22:53:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageListDTO extends SysBaseDTO implements IDictionaryObject {
   @Schema(title="消息类型" , description="TemplateTypeEnum")
    @ApiModelPropertyEnum(TemplateTypeEnum.class)
    private Integer type;

   @Schema(title="消息模板编码" )
    private String templateCode;

   @Schema(title="消息接收者" )
    private Integer receiverId;

   @Schema(title="消息接收者账号" )
    private String receiverAccount;

   @Schema(title="消息发送者" )
    private Integer senderId;

   @Schema(title="消息发送者账号" )
    private String senderAccount;

   @Schema(title="状态" , description="MessageStateEnum")
    @ApiModelPropertyEnum(MessageStateEnum.class)
    private Integer state;

   @Schema(title="消息参数" )
    private String params;

   @Schema(title="消息内容" )
    private String message;


}
