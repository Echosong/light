package cn.light.packet.enums.system;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * 广告位置
 * @author : echosong
 * @version :1.0.0
 */
@Getter
public enum AdPositionEnum implements BaseEnum {
    HOME(10, "首页Banner"),
    //园区
    PARK(20, "园区Banner"),
    //园区下部广告位
    PARK_BOTTOM(30, "园区底部广告位"),
    //商城banner
    SHOP(40, "商城Banner"),
    //引导
    GUIDE(50, "引导");

    private final Integer code;
    private final String name;

    AdPositionEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
