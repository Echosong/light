package com.kdao.light.entity;

import com.kdao.autocode.anno.AutoEntity;
import com.kdao.autocode.anno.AutoEntityField;
import com.kdao.autocode.anno.InQueryDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.Entity;

/**
 * <p>Title: </p >
 * <p>Description: 日志记录</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-10 21:37</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@AutoEntity(value = "日志")
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
public class KdLog extends SysBase {

    @AutoEntityField(value = "请求ip")
    private String requestIp;

    @AutoEntityField(value = "地址")
    private String address;

    @AutoEntityField(value = "描述")
    @InQueryDTO
    private String description;


    @AutoEntityField("浏览器")
    private String browser;

    @AutoEntityField(value = "请求耗时")
    private Long time;

    @AutoEntityField(value = "方法名")
    private String method;

    @AutoEntityField(value = "参数", len = 1000)
    private String params;

    @AutoEntityField(value = "日志类型")
    @InQueryDTO
    private String logType;

    @AutoEntityField(value = "异常详情")
    private byte[] exceptionDetail;
}
