package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.enums.HtmlTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


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
public class SysConfig extends SysBase  {
    @NotBlank
    @AutoEntityField("配置字段名")
    @InQueryDTO
    @Column(name = "`key`")
    private String key;

    @Email
    @CreditCardNumber
    @Length(message = "长度必须为6-16", min = 6, max = 16)
    @AutoEntityField("配置说明")
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
