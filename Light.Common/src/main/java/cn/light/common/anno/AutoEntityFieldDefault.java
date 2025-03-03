package cn.light.common.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <p>Title: 字段设置默认值 </p >
 * <p>Description: AutoDefault</p >
 * <p>create date: 2021-11-29 10:37</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoEntityFieldDefault {
    /**
     * 字符串默认值为空字符串，并且不为null
     */
    String value() default "";

    /**
     * 默认是否为 not null
     */
    boolean notNull() default true;
}
