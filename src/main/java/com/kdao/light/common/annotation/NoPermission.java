package com.kdao.light.common.annotation;

import java.lang.annotation.*;

/**
 * @author Administrator
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoPermission {
}
