package cn.light.entity.entity;


import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import cn.light.packet.enums.business.FinancePayStateEnum;
import cn.light.packet.enums.business.FinancePayTypeEnum;
import cn.light.packet.enums.business.PayTypeEnum;
import cn.light.packet.enums.system.UserRegTypeEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 积分 余额 变动信息
 * @author : 二胡子
 * @version :1.0.0
 */
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "支付记录表")
public class SdFinancePay extends SysBase{
    @AutoEntityField(value = "支付单号")
    private String code;

    @AutoEntityField(value = "支付时间")
    @InQueryDTO
    private Date payTime;

    @AutoEntityField(value = "支付方式")
    private Integer merchantPayId;

    @AutoEntityField(value = "支付方式", enums = PayTypeEnum.class)
    @InQueryDTO
    private Integer payType;

    @AutoEntityField(value = "收款商户名称")
    private String merchantName;

    @AutoEntityField(value = "收款商户ID")
    private Integer merchantId;

    @AutoEntityField(value = "支付业务类型", enums = FinancePayTypeEnum.class)
    private Integer relationType;

    @AutoEntityField(value = "支付金额")
    private BigDecimal payAmount;

    @AutoEntityField(value = "订单状态", enums = FinancePayStateEnum.class)
    @InQueryDTO
    private Integer payState;

    @AutoEntityField(value = "支付描述")
    private String title;

    @AutoEntityField(value = "备注")
    private String remark;

    @AutoEntityField(value = "微信或者公众号OpenId")
    private String openId;

    //支付交易号
    @AutoEntityField(value = "支付交易号")
    private String tradeNo;

    //退款号
    @AutoEntityField(value = "退款号")
    private String refundNo;

    @AutoEntityField(value = "平台", enums = UserRegTypeEnum.class)
    private Integer platform;

    //交互消息说明
    @AutoEntityField(value = "交互消息说明")
    private String msg;

    @AutoEntityField(value = "会员id")
    private Integer memberId;


}
