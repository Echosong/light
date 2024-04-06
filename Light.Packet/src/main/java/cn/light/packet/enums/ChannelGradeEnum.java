package cn.light.packet.enums;

import cn.light.common.enums.BaseEnum;
import lombok.Getter;

/**
 * @author 二胡子
 */

@Getter
public enum ChannelGradeEnum implements BaseEnum {
   //三类
    THREE(1, "三类"),
    //四类
    FOUR(2, "四类"),
    //五类
    FIVE(3, "五类");


    private final Integer code;
    private final String name;

    /**
     * 构造函数
     * @param code 编码id
     * @param name 名称
     */
    ChannelGradeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
