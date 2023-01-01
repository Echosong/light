package com.kdao.light.common.aspect;

import com.kdao.light.common.annotation.NoRepeatSubmit;
import com.kdao.light.common.exception.BaseKnownException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: 重复提交</p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002720:33</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Slf4j
@Aspect
public class RepeatSubmitAspect {

    @Autowired
    private  final StringRedisTemplate redisTemplate;

    @Autowired
    private final HttpServletRequest request;

    public RepeatSubmitAspect(StringRedisTemplate redisTemplate, HttpServletRequest request) {
        this.redisTemplate = redisTemplate;
        this.request = request;
    }

    @Pointcut("@annotation(com.kdao.light.common.annotation.NoRepeatSubmit)")
    public void noRepeat() {
    }

    @Around("noRepeat()")
    public Object before(ProceedingJoinPoint  joinPoint) throws Throwable  {
        // 获取参数值
        MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
        Method method = sign.getMethod();
        NoRepeatSubmit annotation = method.getAnnotation(NoRepeatSubmit.class);
        String sessionId = request.getSession().getId();
        if (sessionId != null) {
            String key = sessionId +"_"+ request.getServletPath();
            String cache =  redisTemplate.opsForValue().get(key);
            if (cache != null) {
                throw  new BaseKnownException(503, "不能重复提交");
            }
            redisTemplate.opsForValue().set(key, "0", annotation.value(), TimeUnit.MILLISECONDS);
        }
        return joinPoint.proceed();

    }




}
