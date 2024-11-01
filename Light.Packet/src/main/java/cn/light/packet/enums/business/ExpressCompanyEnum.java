package cn.light.packet.enums.business;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * 快递公司枚举
 * @author : echosong
 * @version :1.0.0
 */
@Getter
public enum ExpressCompanyEnum implements BaseEnum {
    /**
     * 状态
     */
    SHENG_YUN(1, "申通快递"),
    YU_FENG(2, "圆通快递"),
    YUN_DA(3, "韵达快递"),
    ZHONG_TONG(4, "中通快递"),
    SHUN_FENG(5, "顺丰快递"),
    SHUN_FENG_YUN(6, "顺丰快递"),
    TIAN_DI_HUI(7, "天天快递"),
    HUA_YI_YUN(8, "百世快递"),
    YI_TONG(9, "邮政快递"),
    MAI_LI_YUN(10, "明亮快递"),
    ZHONG_HUA_YUN(11, "中邮快递");


    private final Integer code;
    private final String name;

    ExpressCompanyEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
