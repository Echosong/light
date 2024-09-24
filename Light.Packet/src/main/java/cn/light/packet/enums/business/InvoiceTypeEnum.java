package cn.light.packet.enums.business;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * 广告状态
 * @author : echosong
 * @version :1.0.0
 */
@Getter
public enum InvoiceTypeEnum implements BaseEnum {
    //普通发票
    NORMAL(1, "普通发票"),
    //电子发票
    ELECTRONIC(2, "电子发票"),
    //增值税发票
    VAT(3, "增值税发票"),
    //专用发票
    SPECIAL(4, "专用发票");

    private final Integer code;
    private final String name;

    InvoiceTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
