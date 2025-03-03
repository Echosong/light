package cn.light.packet.enums.system;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * @author 二胡子
 */

@Getter
public enum ConfigGroupEnum  implements BaseEnum {
    /**
     * 合同文档
     */
    ADMIN(10, "基本配置"),
    FILE(40, "文件配置"),
    APP(50, "APP配置");


    private final Integer code;
    private final String name;

    /**
     * 构造函数
     * @param code 编码id
     * @param name 名称
     */
    ConfigGroupEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
