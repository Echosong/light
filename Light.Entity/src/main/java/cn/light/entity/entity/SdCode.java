package cn.light.entity.entity;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

/**
 * @Description: 专门用来生产单据号
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
public class SdCode extends SysBase{

    //表名
    @AutoEntityField(value = "表名")
    private String name;

    @AutoEntityField(value = "单号")
    private Long number;
}
