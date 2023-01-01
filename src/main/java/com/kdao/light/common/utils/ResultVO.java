package com.kdao.light.common.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kdao.light.common.enums.ResultEnum;
import com.kdao.light.common.exception.BaseErrorCode;
import org.springframework.util.Assert;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002720:40</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@SuppressWarnings("unchecked")
public class ResultVO<T> implements BaseErrorCode {
    public static final int SUCCESS = 200;
    private T data;
    private Integer code;
    private String message;

    @JsonIgnore
    public boolean isSuccess() {
        return this.code == 200;
    }

    public ResultVO() {
        this.code = 0;
        this.message = "";
    }

    private ResultVO(ResultEnum result, T data) {
        this.message = result.getMessage();
        this.code = result.getCode();
        this.data = data;
    }

    public ResultVO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public static <T> ResultVO<T> success(T obj) {
        return new ResultVO(ResultEnum.SUCCESS, obj);
    }

    public static ResultVO error(Integer errorCode, String message) {
        Assert.notNull(errorCode, "异常消息编码不能为空");
        Assert.notNull(message, "异常消息不能为空");
        Assert.isTrue(200 != errorCode, "异常消息编码不能等于200");
        Assert.isTrue(0 <= errorCode, "异常消息编码不能小于1");
        return new ResultVO(errorCode, message);
    }

    public static ResultVO error(String message) {
        return new ResultVO(10000, message);
    }

    @Override
    public String toString() {
        return "ResultVO{data=" + this.data + ", code=" + this.code + ", message='" + this.message + '\'' + '}';
    }

    public T getData() {
        return this.data;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
