package com.kdao.light.common.enums;

/**
 * <p>Title: light-controller</p >
 * <p>Description: WifiStrengthEnum</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2022-12-14 22:21 </p >
 *
 * @author : 二胡子
 * @version :1.0.0
 */
public enum WifiStrengthEnum implements BaseEnum{
    /***
     * 设置信息
     */
    MINI(0, "弱"),
    ON(1, "一般"),
    BIG(2,"强");


    private final Integer code;
    private final String name;

    WifiStrengthEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}
