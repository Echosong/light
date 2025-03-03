package cn.light.packet.enums.system;

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
public enum MessageStateEnum implements BaseEnum {
    /**
     *   新建 = 1,
     *   发送 = 2,
     */
    NEW(1, "新建"),
    SEND(2, "发送"),
    READ(3, "已读"),
    DELETE(4, "已删除"),
    ERROR(5, "发送错误");


    private final Integer code;
    private final String name;

    MessageStateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
