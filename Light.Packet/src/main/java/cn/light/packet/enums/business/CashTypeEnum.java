package cn.light.packet.enums.business;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * 支付类型
 *
 * @author : echosong
 * @version :1.0.0
 */
@Getter
public enum CashTypeEnum implements BaseEnum {

    //支付宝
    ALIPAY(1, "支付宝"),
    //微信
    WECHAT(2, "微信"),
    //余额
    BALANCE(3, "银行卡");


    private final Integer code;
    private final String name;

    CashTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
