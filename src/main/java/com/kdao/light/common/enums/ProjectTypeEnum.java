package com.kdao.light.common.enums;

/**
 * <p>Title: FaceSport</p >
 * <p>Description: ProjectTypeEnum</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2022-09-21 11:50 </p >
 *
 * @author : echosong
 * @version :1.0.0
 */
public enum ProjectTypeEnum  implements BaseEnum {
    /**
     * 球类型
     */
    BASKETBALL(0,"篮球"),
    FOOTBALL(1, "足球"),
    BADMINTON(2, "羽毛球"),
    PINGPONG(3, "乒乓球");

    private final Integer code;
    private final String name;


    ProjectTypeEnum(Integer code, String name) {
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
