package cn.light.entity.entity;

import cn.light.common.anno.AutoCover;
import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.enums.CodeTypeEnum;
import cn.light.common.enums.HtmlTypeEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 办公资源文件存在
 * @author : 二胡子
 * @version :1.0.0
 */
@AutoEntity(value = "办公资源文件存在")
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
public class SysResource extends SysBase {
    @AutoEntityField(value = "标题")
    @InQueryDTO
    private String title;

    //类别
    @AutoEntityField(value = "类别", htmlType = HtmlTypeEnum.SELECT)
    @InQueryDTO
    private String type;

    @AutoEntityField(value = "文件路径", htmlType = HtmlTypeEnum.FILE)
    private String path;

    //说明
    @AutoEntityField(value = "说明", htmlType = HtmlTypeEnum.TEXTAREA)
    private String info;
}
