package cn.light.packet.enums;

import cn.light.common.enums.BaseEnum;

/**
 * @author Administrator
 */

public enum ArticleTypeEnum  implements BaseEnum {
    /**
     * 文章类型
     */
    NEW(10, "新闻"),
    NOTICE(20, "公告"),

    ARTICLE(30, "文章"),

    X(40, "通知");

    final Integer code;
    final String name;

    ArticleTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}
