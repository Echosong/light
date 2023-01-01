package com.kdao.light.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * <p>Title: SwaggerConfig</p>
 * <p>Description: swagger 功能开启的配置类,必须要配置h-visions.swagger.enable 为true</p>
 * <p>Company: www.hn1024.cn</p>
 * <p>create date: 2018/9/27</p>
 *
 * @author :leiming
 * @version :1.0.0
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    //上线环境请关闭
    @Value(value = "${k-dao.swagger.enable: true}")
    Boolean swaggerEnabled;

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.OAS_30)
                .enable(swaggerEnabled)
                .securitySchemes(this.security())
                .apiInfo(this.apiInfo())
                .select()//通过select()方法配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                //指定扫描的api包
                //.apis(RequestHandlerSelectors.basePackage("com.kdao.light.api"))
                .build();


    }

    /**
     * 页面统一授权
     * @return
     */
    private List<SecurityScheme> security() {
        ApiKey apiKey = new ApiKey("Authorization", "token", "header");
        return Collections.singletonList(apiKey);
    }

    /**
     * 页面内容相关
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("轻巧之光项目管理系统")
                .description(" http  header 参数 Authorization  算法为 md5(appkey+time(时间戳秒)) appkey 由平台提供 ")
                // 开发者信息
                .contact(new Contact("空道项目管理系统", "http://www.hn1024.cn/",
                        "313690636@qq.com"))
                .version("1.0.0")
                .build();
    }

}