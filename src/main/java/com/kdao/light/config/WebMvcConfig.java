package com.kdao.light.config;

import cn.hutool.core.util.StrUtil;
import com.kdao.light.advice.LoginInterceptor;
import com.kdao.light.config.properties.FileUploadProperties;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Nobita
 * @date 2020/4/18 11:58 上午
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties({FileUploadProperties.class, })
public class WebMvcConfig implements WebMvcConfigurer {

    private final FileUploadProperties fileUploadProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/api/**", "/admin/**")
                .excludePathPatterns("/","/admin/user/login","/api/home/logout");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/admin", c -> c.getPackage().getName().contains("com.kdao.light.controller"))
                .addPathPrefix("/api", c -> c.getPackage().getName().contains("com.kdao.light.api") )
        ;
    }

    /**
     * 配置本地文件上传的虚拟路径和静态化的文件生成路径
     * 备注：这是一种图片上传访问图片的方法，实际上也可以使用nginx反向代理访问图片
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 文件上传
        String uploadFolder = fileUploadProperties.getUploadFolder();
        uploadFolder = StrUtil.appendIfMissing(uploadFolder, File.separator);
        registry.addResourceHandler(fileUploadProperties.getAccessPathPattern())
                .addResourceLocations("file:" + uploadFolder);

    }

}
