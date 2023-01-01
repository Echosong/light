package com.kdao.light;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class LightControllerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LightControllerApplication.class, args);
    }
}
