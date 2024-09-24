package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.packet.enums.business.InvoiceStateEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

/**
 * 发票表
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "财务发票表")
//@AutoCover({CodeTypeEnum.DTO, CodeTypeEnum.LIST_DTO})
public class SdFinanceInvoice extends SysFinanceInvoiceHead {
    //订单id
    @AutoEntityField(value = "订单id")
    private Integer orderId;


    @AutoEntityField(value = "发票金额")
    private Double amount;


    @AutoEntityField(value = "发票状态", enums = InvoiceStateEnum.class)
    private Integer state;

    // 发票日期
    @AutoEntityField(value = "发票日期")
    private Date date;

    @AutoEntityField(value = "发票邮箱")
    private String email;

    @AutoEntityField(value = "发票存储链接")
    private String url;


}
