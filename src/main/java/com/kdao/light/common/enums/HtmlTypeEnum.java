package com.kdao.light.common.enums;

import lombok.Getter;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/28 002816:59</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Getter
public enum HtmlTypeEnum implements BaseEnum{
    /**
     * 显示
     */
    INPUT(0, "文本框"),
    SELECT(1, "下拉选择框"),
    RADIO(2, "switch"),
    CHECKBOX(3, "复选框"),
    UPLOAD(4, "上传"),
    TEXTAREA(5, "文本域"),
    FILE(6,"上传文件"),
    TEXTEDIT(7,"富文本");


    private final Integer code;
    private final String name;

    HtmlTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}
