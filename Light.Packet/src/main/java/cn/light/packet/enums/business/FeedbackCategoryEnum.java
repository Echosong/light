package cn.light.packet.enums.business;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * 广告状态
 * @author : echosong
 * @version :1.0.0
 */
@Getter
public enum FeedbackCategoryEnum implements BaseEnum {
    /**
     * 账号问题
     */
    ACCOUNT(1, "账号问题"),
    /**
     * 账号问题
     */
    APP(2, "APP问题"),
    /**
     * 账号问题
     */
    SERVICE(3, "服务问题"),
    /**
     * 账号问题
     */
    OTHER_SERVICE(4, "其他服务");


    private final Integer code;
    private final String name;

    FeedbackCategoryEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
