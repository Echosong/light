package cn.light.packet.enums.business;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * 广告状态
 * @author : echosong
 * @version :1.0.0
 */
@Getter
public enum AdStateEnum implements BaseEnum {
    /**
     * 状态
     */
    ON(10, "显示"),
    OFF(20, "隐藏");


    private final Integer code;
    private final String name;

    AdStateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
