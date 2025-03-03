package cn.light.common.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <p>Title: </p >
 * <p>Description: 如果某个字段不需要出现在ListDTO加此注解</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0412:15</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface NotinListDTO {
}
