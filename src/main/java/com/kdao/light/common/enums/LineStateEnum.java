package com.kdao.light.common.enums;

/**
 * <p>Title: light-controller</p >
 * <p>Description: LineStateEnum</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2022-12-14 22:19 </p >
 *
 * @author : 二胡子
 * @version :1.0.0
 */
public enum LineStateEnum implements BaseEnum {
    /***
     * 设置信息
     */
    OFF(0, "在线"),
    ON(1, "下线");


    private final Integer code;
    private final String name;

    LineStateEnum(Integer code, String name) {
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
