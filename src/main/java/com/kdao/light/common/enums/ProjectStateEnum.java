package com.kdao.light.common.enums;

/**
 * <p>Title: FaceSport</p >
 * <p>Description: ProjectStateEnum</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2022-09-21 11:10 </p >
 *
 * @author : echosong
 * @version :1.0.0
 */
public enum ProjectStateEnum implements BaseEnum {
    /***
     * 设置信息
     */
    OFF(0, "未上线"),
    ON(1, "已上线");


    private final Integer code;
    private final String name;

    ProjectStateEnum(Integer code, String name) {
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
