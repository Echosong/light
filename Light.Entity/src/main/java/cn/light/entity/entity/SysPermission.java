package cn.light.entity.entity;


import cn.light.common.anno.AutoEntity;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Range;


/**
 * <p>Title: </p >
 * <p>Description: 权限集合</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002719:42</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@AutoEntity(value = "权限")
public class SysPermission extends  SysBase{
    @AutoEntityField("权限名称")
    @NotBlank(message = "权限名称不能为空")
    @InQueryDTO
    private String name;

    @AutoEntityField("权限描述")
    private String description;

    @AutoEntityField("访问路径")
    private String url;

    @AutoEntityField("权限标识")
    @NotBlank(message = "权限标识不能为空")
    private String perms;

    @AutoEntityField("父级id")
    @Range(min = 0)
    private Integer parentId;

    @AutoEntityField(value = "类型", notes = "权限类型 0：目录   1：菜单   2：按钮")
    @Range(min = 0, max = 3)
    private Integer type;

    @AutoEntityField("排序")
    private Integer sort = 0;

    @AutoEntityField("图标")
    private String icon;

    @AutoEntityField("是否展现 1 展现 0 不展现")
    @Column(name = "`show`")
    @TableField("`show`")
    private Integer show;
}
