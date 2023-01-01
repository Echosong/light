package com.kdao.autocode.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <p>Title: </p >
 * <p>Description: 类上做处理</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0411:44</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoEntity {
    /**
     * 表说明
     */
    String value() default "";

    /**
     * 是否生成DTO
     */
    boolean dto() default true;

    /**
     * 是否生成前端list.vue
     */
    boolean viewList() default true;

    /**
     * 是否要生产表单文件
     */
    boolean viewFrom() default true;

    /**
     * 是否需要生成对应的controller
     */
    boolean controller() default  true;

    /**
     * controller 需要生成那些对应方法
     */
    String  controllerFunction() default "listPage,save,delete,find,list";
}
