package com.kdao.light.common.annotation;

import com.kdao.light.common.enums.BusinessEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title: 日志 </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-10 21:34</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value() default "";

    BusinessEnum businessType() default  BusinessEnum.OTHER;


}
