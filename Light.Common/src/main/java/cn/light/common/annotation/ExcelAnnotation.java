package cn.light.common.annotation;

import java.lang.annotation.*;

/**
 * <p>Title: 导出时候忽略 </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/29 002912:20</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelAnnotation {
    boolean ignore() default false;

    int width() default 20;
}
