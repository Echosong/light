package cn.light.generator;

import cn.hutool.core.lang.Singleton;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.light.common.anno.AutoEntity;
import cn.light.generator.config.Config;
import cn.light.generator.config.Const;
import cn.light.generator.processor.*;
import jakarta.persistence.Entity;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * 启动类
 * @author : 二胡子
 * @version :1.0.0
 */
@Slf4j
public class Bootstrap {
    public static void main(String[] args) {
        //初始化配置
        Config.initialization();

        Const.SYS_PATH = ClassUtil.getPackagePath(Bootstrap.class).replace("/", ".")
                .replace(".generator", "");
        if (StrUtil.isBlank(Const.ROOT_PATH)) {
            throw new RuntimeException("项目路路径");
        }
        //正式处理业务
        Set<Class<?>> classes = ClassUtil.scanPackage(Const.SYS_PATH + ".entity.entity");

        long start = System.currentTimeMillis();


        //生成数据字典
        CompletableFuture.runAsync(() -> {
            log.info("=================================开始生成 数据字典=========================================");
            for (Class<?> clazz : classes) {
                if (clazz.isAnnotationPresent(Entity.class)) {
                    Singleton.get(DictionaryService.class, clazz).start();
                }
            }
            DictionaryService.writeDictionaryFile();
            log.info("=========================结束生成 数据字典 :: " + (System.currentTimeMillis() - start) + " ms======================");
        }, Executors.newSingleThreadExecutor());

        //数据库脚本处理
        CompletableFuture.runAsync(() -> {
            log.info("==========================开始 数据库处理============================================= ");
            for (Class<?> clazz : classes) {
                if (clazz.isAnnotationPresent(Entity.class) && clazz.isAnnotationPresent(AutoEntity.class)) {
                    Singleton.get(JpaCommentService.class, clazz).start();
                }
            }
            log.info("==============================结束生成 数据库处理 执行时长 " + (System.currentTimeMillis() - start) + " ms=====================");
        }, Executors.newSingleThreadExecutor());


        //DTO 处理
        CompletableFuture.runAsync(() -> {
            log.info("============================开始 生成Mapper+DTO对象 ========================= ");
            for (Class<?> clazz : classes) {
                if (clazz.isAnnotationPresent(Entity.class) && clazz.isAnnotationPresent(AutoEntity.class)) {
                    AutoEntity autoEntity = clazz.getAnnotation(AutoEntity.class);
                    if (autoEntity.dto()) {
                        Singleton.get(DtoService.class, clazz).start();
                    }
                    Singleton.get(MapperService.class, clazz).start();
                }
            }
            log.info("====================结束生成 生成 Mapper+ DTO对象， 执行时长 " + (System.currentTimeMillis() - start) + " ms============================");
        }, Executors.newSingleThreadExecutor());


        //视图 处理
        CompletableFuture.runAsync(() -> {
            log.info("=========================开始 视图 ==================================================== ");
            for (Class<?> clazz : classes) {
                if (clazz.isAnnotationPresent(Entity.class) && clazz.isAnnotationPresent(AutoEntity.class)) {
                    try {
                        //视图处理
                        Singleton.get(ViewService.class, clazz).start();
                    }catch (Exception e){
                        log.error("视图处理失败",e);
                    }
                }
            }
            log.info("==========================结束生成 生成视图， 执行时长 " + (System.currentTimeMillis() - start) + " ms===========================");
        }, Executors.newSingleThreadExecutor());



        //控制器 处理
        CompletableFuture.runAsync(() -> {
            log.info("================================开始 控制器 ========================================== ");
            for (Class<?> clazz : classes) {
                if (clazz.isAnnotationPresent(Entity.class) && clazz.isAnnotationPresent(AutoEntity.class)) {
                    AutoEntity autoEntity = clazz.getAnnotation(AutoEntity.class);
                    if(autoEntity.controller()) {
                        Singleton.get(ControllerService.class, clazz).start();
                    }
                }
            }
            log.info("==================================结束生成 控制器， 执行时长 " + (System.currentTimeMillis() - start) + " ms============================");
        }, Executors.newSingleThreadExecutor());



        //控制器 处理
        CompletableFuture.runAsync(() -> {
            log.info("===============================开始 业务服务 =========================================== ");
            for (Class<?> clazz : classes) {
                if (clazz.isAnnotationPresent(Entity.class) && clazz.isAnnotationPresent(AutoEntity.class)) {
                    AutoEntity autoEntity = clazz.getAnnotation(AutoEntity.class);
                    if(autoEntity.service()) {
                        Singleton.get(ImplService.class, clazz).start();
                    }
                }
            }
            log.info("================================结束生成 业务服务， 执行时长 " + (System.currentTimeMillis() - start) + " ms========================");
        }, Executors.newSingleThreadExecutor());


    }
}
