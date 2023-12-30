package com.kdao.light.entity;

import com.kdao.autocode.anno.AutoEntity;
import com.kdao.autocode.anno.AutoEntityField;
import com.kdao.autocode.anno.InQueryDTO;
import com.kdao.light.common.enums.HtmlTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>Title: </p >
 * <p>Description: 系统配置</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/28 002816:52</p >
 *
 * @author : echosong
 * @version :1.0.0
 */

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "系统配置", controllerFunction = "list,save")
public class KdConfig extends SysBase  {
    @AutoEntityField("配置字段名")
    @NotBlank
    @InQueryDTO
    @Column(name = "`key`")
    private String key;

    @AutoEntityField("配置说明")
    @NotBlank(message = "不能为空")
    @InQueryDTO
    private String name;

    @AutoEntityField("配置分组")
    @Range(min = 0)
    @Column(name="`group`")
    private Integer group = 0;

    @AutoEntityField("配置值")
    private String value;

    @AutoEntityField( value = "类型", notes = "配置html展现类型", enums = HtmlTypeEnum.class)
    @Range(min = 0, max = 10, message = "必须大于")
    private Integer type = 0;

    @Pattern(regexp = "\\s{2,}", message = "必须大于2")
    @AutoEntityField(value = "描述", notes = "如果选择框用,隔开")
    private String description;
}
