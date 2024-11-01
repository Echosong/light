package cn.light.packet.enums.business;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * 审核状态
 * @author : echosong
 * @version :1.0.0
 */
@Getter
public enum AuditStateEnum implements BaseEnum {
   //等待审核
    WAIT_AUDIT(10, "等待审核"),
    //审核通过
    AUDIT_PASS(20, "审核通过"),
    //审核不通过
    AUDIT_FAIL(30, "审核不通过");


    private final Integer code;
    private final String name;

    AuditStateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
