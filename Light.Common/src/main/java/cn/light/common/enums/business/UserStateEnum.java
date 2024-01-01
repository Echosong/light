package cn.light.common.enums.business;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * <p>Title: </p >
 * <p>Description: 用户状态</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0116:16</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Getter
public enum UserStateEnum implements BaseEnum {
    /**
     * 用户状态
     */
    NEW(0, "未启用"),
    ON(1, "正常"),
    OFF(2, "禁用"),
    DELETE(3,"删除");


    private final Integer code;
    private final String name;

    UserStateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
