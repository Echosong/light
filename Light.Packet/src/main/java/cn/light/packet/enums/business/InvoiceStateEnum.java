package cn.light.packet.enums.business;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * 广告状态
 *
 * @author : echosong
 * @version :1.0.0
 */
@Getter
public enum InvoiceStateEnum implements BaseEnum {

    //新申请
    NEW(0, "新申请"),
    //已通过
    PASS(1, "已通过"),
    //已拒绝
    REJECT(2, "已拒绝"),
    //已作废
    VOID(3, "已作废"),
    OPEN(4, "已开票");


    private final Integer code;
    private final String name;

    InvoiceStateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
