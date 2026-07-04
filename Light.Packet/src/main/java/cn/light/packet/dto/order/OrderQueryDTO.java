package cn.light.packet.dto.order;

import cn.light.packet.enums.system.*;
import cn.light.packet.enums.business.*;
import cn.light.common.dto.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.light.common.annotation.ApiModelPropertyEnum;
import lombok.Data;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.*;
/**
* OrderQueryDTO 查询DTO
* email:zq_songfeigang@163.com
*
* @author : 二胡子
* @version : 1.0
* @date : 2026-07-04 11:26:22
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderQueryDTO extends PageInfo {

    @Schema(title = "用户账号")
    private String memberName;

    @Schema(title = "订单号")
    private String orderNo;

    @Schema(title = "外部订单号")
    private String outOrderNo;

    @Schema(title = "支付金额")
    private BigDecimal payAmount;

}
