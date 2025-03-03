package cn.light.common.anno;

import cn.light.common.enums.BaseEnum;
import cn.light.common.enums.HtmlTypeEnum;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <p>Title: </p >
 * <p>Description: 字段相关注解</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0411:39</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoEntityField {

    /**
     * 字段名称
     */
    String value() default "";

    /**
     * 详细说明
     */
    String notes() default "";

    /**
     * 是否生成DTO对应字段
     */
    boolean dto() default true;


    /**
     * 数据原routeName
     * @return 数据源
     */
    String source() default "";


    /**
     * 字符长度
     */
    int len() default 255;

    /**
     * 是否为主名称 getMap使用
     */
    boolean isKeyName() default false;


    /**
     * 表单类型
     */
    HtmlTypeEnum htmlType() default HtmlTypeEnum.INPUT;

    /**
     * 枚举
     */
    Class<? extends BaseEnum>  enums() default  BaseEnum.class;


}
