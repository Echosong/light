package cn.light.packet.enums;

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
    APP(1, "app"),
    /**
     * 相关配置
     */
    ADMIN(0, "后台"),

    /**
     * 上传配置
     */
    FILE(2, "上传");


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
