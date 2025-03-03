package cn.light.entity.entity;

import cn.light.common.anno.AutoEntityField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * <p>Title: </p >
 * <p>Description: 基础实体</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002719:30</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class SysBase {

    @AutoEntityField(value = "版本号，自动实现乐观锁")
    private Integer version;

    /**
     * 主键 这里跟AUTO 要有区分
     */
    @Id
    @TableId(type = IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @AutoEntityField(value = "编号")
    protected Integer id;

    /**
     * 创建时间
     */
    @Column(updatable = false)
    @CreatedDate
    @AutoEntityField(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    protected Date createTime;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @AutoEntityField(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected Date updateTime;

    /**
     * 创建人
     */
    @Column(updatable = false)
    @CreatedBy
    @AutoEntityField(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    protected Integer creatorId;

    /**
     * 修改人
     */
    @LastModifiedBy
    @AutoEntityField(value = "修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected Integer updaterId;


}
