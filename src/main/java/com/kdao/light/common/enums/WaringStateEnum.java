package com.kdao.light.common.enums;

/**
 * <p>Title: light-controller</p >
 * <p>Description: WaringStateEnum</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2022-12-14 22:44 </p >
 *
 * @author : 二胡子
 * @version :1.0.0
 */
public enum WaringStateEnum implements BaseEnum {
    /**
     * 报警
     */
    UNDO(0, "未处理"),
    DONIG(1, "处理中"),
    DONE(2,"已经处理");

    private final Integer code;
    private final String name;

    WaringStateEnum(Integer code, String name){
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }



    @Override
    public String getName() {
        return this.name;
    }
}
