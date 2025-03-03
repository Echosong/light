package cn.light.packet.enums.system;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

@Getter
public enum UserRegTypeEnum implements BaseEnum {
    /**
     * 注册类型
     */
    NEW(10, "线下注册"),
    WX(20, "微信"),
    ALIPAY(30, "支付宝");


    private final Integer code;
    private final String name;

    private UserRegTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
