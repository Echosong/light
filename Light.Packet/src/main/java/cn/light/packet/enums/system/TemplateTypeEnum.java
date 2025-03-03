package cn.light.packet.enums.system;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * @author hz
 */

@Getter
public enum TemplateTypeEnum implements BaseEnum {
    /**
     * 转发 = 1,
     * 验证码短信 = 2,
     * 通知短信 = 3,
     * 推送消息 = 4,
     * 站内消息 = 5,
     * 推送模板消息 = 6
     */

    FORWARD(1, "转发"),
    VERIFY_CODE(2, "验证码短信"),
    NOTIFICATION(3, "通知短信"),
    PUSH(4, "推送消息"),
    INTERNET(5, "站内消息"),
    PUSH_TEMPLATE(6, "推送模板消息");

    private final Integer code;
    private final String name;

    TemplateTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
