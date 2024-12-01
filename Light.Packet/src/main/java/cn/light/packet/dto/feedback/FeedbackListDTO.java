package cn.light.packet.dto.feedback;

import cn.light.packet.enums.system.*;
import cn.light.packet.enums.business.*;
import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.PageInfo;
import cn.light.common.dto.SysBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.*;



/**
 * Feedback列表传输对象
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-10-05 21:25:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FeedbackListDTO extends SysBaseDTO implements IDictionaryObject {
    @Schema(title = "用户账号")
    private String userAccount;

    @Schema(title = "图片")
    private String images;

    @Schema(title = "内容")
    private String content;

    @Schema(title = "联系方式")
    private String contact;

    @Schema(title = "是否已读")
    private Short isRead;

    @Schema(title = "意见反馈问题分类", description = "FeedbackCategoryEnum")
    @ApiModelPropertyEnum(FeedbackCategoryEnum.class)
    private Integer category;


}
