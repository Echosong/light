package com.kdao.light;

import com.github.lkqm.spring.jpa.repository.config.EnableMybatisJpaRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@EnableMybatisJpaRepositories
public class LightControllerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LightControllerApplication.class, args);
    }
}
