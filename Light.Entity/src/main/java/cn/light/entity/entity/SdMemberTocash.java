package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import cn.light.packet.enums.business.UserTocashStateEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * @Description: 用户提现记录表
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "用户提现记录表")
public class SdMemberTocash extends SysBase{
    @AutoEntityField(value = "用户id")
    private Integer memberId;

    @AutoEntityField(value = "用户账号")
    @InQueryDTO
    private String memberName;

    @AutoEntityField(value = "提现金额")
    @Min(value = 0, message = "提现金额不能小于0")
    private BigDecimal money;

    @AutoEntityField(value = "余额")
    private Integer balance;

    @AutoEntityField(value = "提现方式", enums = CashTypeEnum.class)
    private Integer type;

    @AutoEntityField(value = "提现状态", enums = UserTocashStateEnum.class)
    private Integer state;

    @AutoEntityField(value = "提现手续费")
    @Min(0)
    private BigDecimal withdrawals;

    //备注
    @AutoEntityField(value = "备注")
    private String remark;

}
