package cn.light.common.annotation;

import java.lang.annotation.*;

/**
 * <p>Title: 忽略使用统一格式输出 </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002720:43</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiResultIgnore {
}
