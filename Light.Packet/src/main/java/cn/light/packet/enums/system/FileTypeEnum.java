package cn.light.packet.enums.system;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * <p>Title: </p >
 * <p>Description: 文件类型业务划分</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/29 002922:08</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Getter
public enum FileTypeEnum implements BaseEnum {
    /**
     * 合同文档
     */
    CONTACT(1, "音频"),
    /**
     * 相关配置
     */
    CONFIG(2, "视频");


    private final Integer code;
    private final String name;

    /**
     * 构造函数
     * @param code 编码id
     * @param name 名称
     */
    FileTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
