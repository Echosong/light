package com.kdao.light.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.kdao.light.common.aspect.RepeatSubmitAspect;
import com.kdao.light.common.component.DictionarySerializer;
import com.kdao.light.common.component.IDictionaryObject;
import com.kdao.light.common.component.SerialUtil;
import com.kdao.light.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002720:30</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Configuration
public class Config {

    /**
     * redis处理对象
     */
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserService userService;

    /**
     * Date格式化字符串
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * DateTime格式化字符串
     */
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * Time格式化字符串
     */
    private static final String TIME_FORMAT = "HH:mm:ss";

    /**
     * 统一处理返回json
     * @param stringRedisTemplate
     * @param userService
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer initJackson(StringRedisTemplate stringRedisTemplate, UserService userService) {
        return builder -> {
            //这里的ByType 可以失败类类型，属性类型
            builder.serializerByType(IDictionaryObject.class, new DictionarySerializer(stringRedisTemplate,userService));
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT)));
            builder.serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT)));
            builder.serializerByType(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(TIME_FORMAT)));
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT)));
            builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_FORMAT)));
            builder.deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(TIME_FORMAT)));
        };
    }

    /**
     * 注入重复提交切面
     * @param stringRedisTemplate
     * @param request
     * @return
     */
    @Bean
    RepeatSubmitAspect repeatSubmitAspect(StringRedisTemplate stringRedisTemplate, HttpServletRequest request){
        return new RepeatSubmitAspect(stringRedisTemplate, request);
    }


    /***
     * 注入redis 得到 生成自增code bean
     * @param stringRedisTemplate
     * @return
     */
    @Bean
    SerialUtil getSerialUtil(StringRedisTemplate stringRedisTemplate) {
        return new SerialUtil(stringRedisTemplate);
    }
}
