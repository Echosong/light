package cn.light.packet.enums;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * @author 二胡子
 */

@Getter
public enum YesOrNoEnum implements BaseEnum {
    /**
     * 合同文档
     */
    ON(1, "开启"),
    /**
     * 相关配置
     */
    OFF(0, "关闭");


    private final Integer code;
    private final String name;

    /**
     * 构造函数
     * @param code 编码id
     * @param name 名称
     */
    YesOrNoEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
