package com.kdao.light.entity;

import com.kdao.autocode.anno.AutoEntity;
import com.kdao.autocode.anno.AutoEntityField;
import com.kdao.autocode.anno.InQueryDTO;
import com.kdao.autocode.anno.NotinListDTO;
import com.kdao.light.common.enums.HtmlTypeEnum;
import jakarta.persistence.Entity;
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

    @AutoEntityField(value = "产品名称")
    @InQueryDTO
    private String name;

    @AutoEntityField(value = "价格")
    private BigDecimal price;


}
