package com.kdao.light.common.exception;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002720:53</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
public class BaseKnownException extends RuntimeException implements BaseErrorCode {
    private final Integer code;
    private final String message;

    public BaseKnownException(BaseErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public BaseKnownException(String errorMessage){
        this.code = 500;
        this.message = errorMessage;
    }

    public BaseKnownException(Integer errorCode, String errorMessage) {
        this.code = errorCode;
        this.message = errorMessage;

    }

    public BaseKnownException() {
        this.code = -1;
        this.message = "报错信息未设置";
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