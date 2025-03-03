package cn.light.common.annotation;



import cn.light.common.enums.BaseEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 枚举类字段属性的注解
 *
 * @author listen
 * @date 2019/05/16 15:18
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiModelPropertyEnum {

    /**
     * 枚举类对象
     *
     */
    Class<? extends BaseEnum> value();

    String example() default  "";

    /**
     * 是否隐藏
     *
     */
    boolean hidden() default false;

    /**
     * 是否必须
     *
     */
    boolean required() default true;


    String enumDesc() default "";

}
