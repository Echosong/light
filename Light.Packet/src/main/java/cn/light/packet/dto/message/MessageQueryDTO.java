package cn.light.packet.dto.message;

import cn.light.common.dto.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.light.common.annotation.ApiModelPropertyEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.*;

import cn.light.packet.enums.system.TemplateTypeEnum;

/**
 * MessageQueryDTO 查询DTO
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-08-04 22:53:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageQueryDTO extends PageInfo {
    @Schema(title = "消息类型", description = "TemplateTypeEnum")
    @ApiModelPropertyEnum(TemplateTypeEnum.class)
    private Integer type;

    @Schema(title = "消息模板编码")
    private String templateCode;

    @Schema(title = "消息接收者")
    private Integer receiverId;

    @Schema(title = "消息接收者账号")
    private String receiverAccount;


}
