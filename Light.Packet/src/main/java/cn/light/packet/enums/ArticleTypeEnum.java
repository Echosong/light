package cn.light.packet.enums;

import cn.light.common.enums.BaseEnum;

/**
 * @author Administrator
 */

public enum ArticleTypeEnum  implements BaseEnum {
    /**
     * 文章类型
     */
    NEW(0, "新闻"),
    NOTICE(1, "公告"),

    ARTICLE(3, "文章");

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
