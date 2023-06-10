package com.kdao.light.common.annotation;

import java.lang.annotation.*;

/**
 * 不需要登录就能访问加这个注解
 * @author 二胡子
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoPermission {
}
