package cn.light.packet.enums.business;

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
public enum GoodStateEnum implements BaseEnum {
    REFUSE(4, "审核拒绝"),
    ON(3, "已上架"),
    ONING(2, "上架审核中"),
    OFF(1, "已下架"),
    DRAFTS(0, "草稿箱");

    private final Integer code;
    private final String name;

    GoodStateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
