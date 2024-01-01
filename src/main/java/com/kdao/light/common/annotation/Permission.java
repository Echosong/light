package com.kdao.light.common.annotation;

import com.kdao.light.common.enums.LogicalEnum;

import java.lang.annotation.*;

/**
 * 注解权限控制
 * @author 二胡子
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

    /**
     * 角色
     */
    String[] roles() default {};

    /**
     * 权限码
     */
    String[] permissions() default {};

    /**
     * 逻辑运算符
     */
    LogicalEnum logical() default LogicalEnum.AND;
}
