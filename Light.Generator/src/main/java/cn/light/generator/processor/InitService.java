package cn.light.generator.processor;

import cn.hutool.core.lang.Singleton;
import cn.light.common.anno.AutoEntity;
import jakarta.persistence.Entity;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0412:23</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Slf4j
public class InitService {
    /**
     * 单个Entity 处理
     *
     * @param clazz 当前类的类型
     */
    public void run(Class<?> clazz) {

        // 类上面拿注解
        // 生成 repository
        if (clazz.isAnnotationPresent(Entity.class)) {
            Singleton.get(RepositoryService.class, clazz).start();
        }
        //做表备注
        if (clazz.isAnnotationPresent(AutoEntity.class)) {
            AutoEntity autoEntity = clazz.getAnnotation(AutoEntity.class);

            //处理数据库备注相关
            Singleton.get(JpaCommentService.class, clazz).start();

            //DTO处理
            if (autoEntity.dto()) {
                Singleton.get(DtoService.class, clazz).start();
                Singleton.get(MapperService.class, clazz).start();
            }

            //视图处理
            Singleton.get(ViewService.class, clazz).start();

            //控制器处理
            if (autoEntity.controller()) {
                Singleton.get(ControllerService.class, clazz).start();
            }

            //service处理
            if (autoEntity.service()) {
                Singleton.get(ImplService.class, clazz).start();
            }

            //数据字典写入
            Singleton.get(DictionaryService.class, clazz).start();


        }


    }
}
