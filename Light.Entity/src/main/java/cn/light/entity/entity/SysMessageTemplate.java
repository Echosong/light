package cn.light.entity.entity;

import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.enums.HtmlTypeEnum;
import cn.light.packet.enums.system.TemplateTypeEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

/**
 * SdMessageTemplate
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 * @date : 2024/8/4 22:35
 */

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "消息模板", controller = false, service = false, viewList = false, viewFrom = false, viewInfo = false)
public class SysMessageTemplate extends  SysBase{

    @AutoEntityField(value = "模板类型", enums = TemplateTypeEnum.class)
    private Integer type;

    @AutoEntityField(value = "模板编码")
    @Length(min = 0, max = 100, message = "模板编码长度不能超过100")
    @InQueryDTO
    private String code;

    //限定参数
    @AutoEntityField(value = "限定参数")
    private String params;

    //模板说明
    @AutoEntityField(value = "模板说明")
    private String title;

    //模板内容
    @AutoEntityField(value = "模板内容", htmlType = HtmlTypeEnum.TEXTAREA)
    private String content;
}
