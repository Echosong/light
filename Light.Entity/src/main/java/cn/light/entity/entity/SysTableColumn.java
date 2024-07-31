package cn.light.entity.entity;

import cn.light.common.anno.AutoCover;
import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.enums.CodeTypeEnum;
import cn.light.common.enums.HtmlTypeEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * SysTableColumn
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 * @date : 2024/7/28 22:12
 */

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "存储列设置表", viewInfo = false,
        viewList = false, viewFrom = false)
//@AutoCover(CodeTypeEnum.SERVICE)
public class SysTableColumn extends SysBase{

    @AutoEntityField(value = "用户id")
    private Integer userId;

    @AutoEntityField(value = "表名")
    private String tableName;

    @AutoEntityField(value = "表列", htmlType = HtmlTypeEnum.TEXTAREA)
    private String columns;
}
