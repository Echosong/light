package com.kdao.light.common.enums;

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
public enum BusinessEnum implements BaseEnum{
    /**
     * 合同文档
     */
    LOGIN(10, "登录"),
    /**
     * 相关配置
     */
    TEST(20, "测试"),

    OTHER(50, "其他");


    private final Integer code;
    private final String name;

    /**
     * 构造函数
     * @param code 编码id
     * @param name 名称
     */
    BusinessEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
