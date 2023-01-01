package com.kdao.light.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
    /**
     * 主键 这里跟AUTO 要有区分
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    /**
     * 创建时间
     */
    @Column(updatable = false)
    @CreatedDate
    protected Date createTime;

    /**
     * 修改时间
     */
    @LastModifiedDate
    protected Date updateTime;

    /**
     * 创建人
     */
    @Column(updatable = false)
    @CreatedBy
    protected Integer creatorId;

    /**
     * 修改人
     */
    @LastModifiedBy
    protected Integer updaterId;


}
