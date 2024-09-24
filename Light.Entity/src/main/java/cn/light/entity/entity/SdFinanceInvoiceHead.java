package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@AutoEntity(value = "发票抬头",controller = false, viewFrom = false,viewList = false, service = false)
//@AutoCover({CodeTypeEnum.DTO, CodeTypeEnum.LIST_DTO})
public class SdFinanceInvoiceHead extends SysFinanceInvoiceHead {


}
