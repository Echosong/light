package cn.light.entity.entity;


import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

/**
 * <p>Title: </p >
 * <p>Description: 日志记录</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-10 21:37</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@AutoEntity(value = "产品信息")
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
public class KdProduct extends SysBase {

    @Size
    @Column
    @AutoEntityField(value = "产品名称")
    @InQueryDTO
    private String name;

    @AutoEntityField(value = "价格")
    private BigDecimal price;


}
