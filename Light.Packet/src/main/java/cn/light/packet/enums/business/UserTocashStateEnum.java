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
public enum UserTocashStateEnum implements BaseEnum {
    /**
     * 用户状态
     */
    NEW(10, "新申请"),
    ING(20, "提现中"),
    SUCCESS(30, "同意提现"),
    FAIL(40, "提现失败"),
    //拒绝
    REFUSE(45, "拒绝提现"),
    CANCEL(50, "取消提现"),
    //打款
    DONE(60, "打款成功"),
    ;


    private final Integer code;
    private final String name;

    UserTocashStateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
