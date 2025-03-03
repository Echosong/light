package cn.light.packet.enums.business;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * 广告状态
 * @author : echosong
 * @version :1.0.0
 */
@Getter
public enum InvoiceCategoryEnum implements BaseEnum {
    /**
     * 状态
     */
    //个人
    INVOICE_CATEGORY_PERSONAL(1, "个人"),
    INVOICE_CATEGORY_ENTERPRISE(2, "企业");

    private final Integer code;
    private final String name;

    InvoiceCategoryEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
