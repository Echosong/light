package cn.light.common.annotation;


import cn.light.common.enums.LogicalEnum;

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

    String message() default "权限不足不能访问";
}
