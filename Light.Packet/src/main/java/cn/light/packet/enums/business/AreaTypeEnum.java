package cn.light.packet.enums.business;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * 广告状态
 * @author : echosong
 * @version :1.0.0
 */
@Getter
public enum AreaTypeEnum implements BaseEnum {
    /**
     * 状态
     */
    PROVINCE(1, "省"),
    CITY(2, "市"),
    COUNTY(3, "区/县");


    private final Integer code;
    private final String name;

    AreaTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
