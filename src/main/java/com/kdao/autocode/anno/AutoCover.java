package com.kdao.autocode.anno;

import com.kdao.autocode.enums.CodeTypeEnum;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <p>是否覆盖 </p >
 * <p>Description: 代码更新时候是否要覆盖,java 代码不能直接删掉，因为会报相关错误，所以只能替换</p >
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
    CodeTypeEnum[] value() default {CodeTypeEnum.NULL}  ;

}
