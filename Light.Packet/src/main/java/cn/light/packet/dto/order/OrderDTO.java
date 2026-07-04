package cn.light.packet.dto.order;

import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.SysBaseDTO;
import cn.light.packet.enums.system.*;
import cn.light.packet.enums.business.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.*;
/**
 * 订单通用信息 pojo转化
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderDTO extends SysBaseDTO implements IDictionaryObject {

    @Schema(title = "版本号，自动实现乐观锁")
    private Integer version;

    @Schema(title = "编号")
    private Integer id;

    @Schema(title = "创建时间")
    private Date createTime;

    @Schema(title = "更新时间")
    private Date updateTime;

    @Schema(title = "创建人")
    private Integer creatorId;

    @Schema(title = "修改人")
    private Integer updaterId;

    @Schema(title = "用户账号")
    private String memberName;

    @Schema(title = "订单号")
    private String orderNo;

    @Schema(title = "用户ID")
    private Integer memberId;

    @Schema(title = "外部订单号")
    private String outOrderNo;

    @Schema(title = "市场价")
    private BigDecimal marketAmount;

    @Schema(title = "定金")
    private BigDecimal earnestAmount;

    @Schema(title = "协议价")
    private BigDecimal costAmount;

    @Schema(title = "支付金额")
    private BigDecimal payAmount;

    @Schema(title = "支付时间")
    private Date payTime;

    @Schema(title = "消费积分")
    private Integer point;

    @Schema(title = "赠送积分")
    private Integer givePoint;

    @Schema(title = "订单标题")
    private String title;

    @Schema(title = "供应商ID")
    private Integer supplierId;

    @Schema(title = "供应商名称")
    private String shopName;

    @Schema(title = "支付相关id")
    private Integer financePayId;

    @Schema(title = "发票抬头id")
    private Integer invoiceHeadId;

}
