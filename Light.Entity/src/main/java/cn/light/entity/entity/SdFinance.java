package cn.light.entity.entity;


import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import cn.light.packet.enums.business.FinanceTypeEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

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
@AutoEntity(value = "余额变动")
public class SdFinance extends SysBase{

    @AutoEntityField(value = "关联业务id")
    private Integer relateId;

    @AutoEntityField(value = "关联业务号")
    private String relateNo;


    @AutoEntityField(value = "会员id")
    @InQueryDTO
    private Integer memberId;

    @AutoEntityField(value = "会员账号")
    @InQueryDTO
    private String memberName;

    @AutoEntityField(value = "业务类型", enums = FinanceTypeEnum.class)
    @InQueryDTO
    private Integer type;

    @AutoEntityField(value = "处理金额")
    private BigDecimal money;

    @AutoEntityField(value = "变动前")
    private BigDecimal beginMoney;

    @AutoEntityField(value = "变动后")
    private BigDecimal afterMoney;

    @AutoEntityField("标题")
    @InQueryDTO
    private String title;

}
