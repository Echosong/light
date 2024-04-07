package cn.light.packet.enums;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * 存储类型
 * @author 二胡子
 */

@Getter
public enum StorageTypeEnum implements BaseEnum {
    /**
     * 本地存储
     */
    LOCAL(10, "本地存储"),
    /**
     * 相关配置
     */
    OSS(20, "阿里云OSS");


    private final Integer code;
    private final String name;

    /**
     * 构造函数
     * @param code 编码id
     * @param name 名称
     */
    StorageTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
