package cn.light.common.config;

import cn.light.common.aspect.RateLimiterAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * AutoConfiguration
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 * @date : 2025/5/31 10:47
 */
@Configuration
@ComponentScan(basePackages = {"cn.light.common.advice", "cn.light.common.component"})
public class AutoConfiguration {

    @Bean
    public RateLimiterAspect rateLimiterAspect() {
        return new RateLimiterAspect();
    }
}
