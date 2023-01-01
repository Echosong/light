package com.kdao.light.common.enums;

import lombok.Getter;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002720:41</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Getter
public enum ResultEnum {
    /**
     * 字段信息
     */
    SUCCESS(200, "成功"),
    ERROR(1, "失败");

    private final int code;
    private final String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {return  this.message;}
}

