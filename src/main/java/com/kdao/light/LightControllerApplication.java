package com.kdao.light;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 启动文件
 * @author 二胡子
 */
@EnableCaching
@SpringBootApplication

@Slf4j
public class LightControllerApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(LightControllerApplication.class, args);
        }catch (Exception e){
            //加入捕捉错误，启动不了的时候可以看到原因
            log.error("启动失败",e);
        }

    }
}
