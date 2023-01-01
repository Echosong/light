package com.kdao.light.config;

import com.kdao.light.common.annotation.ApiModelPropertyEnumPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

/**
 * <p>Title: </p >
 * <p>Description: 扩展注解</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-10-310:36</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Configuration
public class SwaggerPluginConfig {
    @Bean
    @Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1)
    public ApiModelPropertyEnumPlugin swaggerEnum(){
        return new ApiModelPropertyEnumPlugin();
    }
}
