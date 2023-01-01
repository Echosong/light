package com.kdao.light.common.enums;

import lombok.Getter;

@Getter
public enum UserRegTypeEnum implements BaseEnum{
    /**
     * 注册类型
     */
    NEW(0, "线下注册"),
    WX(1, "微信"),
    ALIPAY(2, "支付宝");


    private final Integer code;
    private final String name;

    private UserRegTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
