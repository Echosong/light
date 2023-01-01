package com.kdao.autocode.anno;

import com.kdao.autocode.enums.CodeTypeEnum;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <p>Title: </p >
 * <p>Description: 代码更新时候是否要覆盖</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0411:44</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoCover {
    /**
     * 覆盖的对象
     */
    CodeTypeEnum value() default CodeTypeEnum.NULL  ;
}
