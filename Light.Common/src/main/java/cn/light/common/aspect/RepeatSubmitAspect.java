package cn.light.common.aspect;

import cn.light.common.annotation.NoRepeatSubmit;
import cn.light.common.exception.BaseKnownException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: 防重复提交切面</p >
 * <p>Description: 使用Redis实现分布式防重复提交，支持线程安全</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002720:33</p >
 *
 * @author : echosong
 * @version :2.0.0
 */
@Slf4j
@Aspect
@Component
public class RepeatSubmitAspect {

    private final StringRedisTemplate redisTemplate;
    
    private static final String REPEAT_SUBMIT_KEY_PREFIX = "repeat_submit:";
    private static final String DEFAULT_ERROR_MESSAGE = "请勿重复提交";

    /**
     * 构造函数 - 移除HttpServletRequest注入以避免线程安全问题
     */
    public RepeatSubmitAspect(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Pointcut("@annotation(cn.light.common.annotation.NoRepeatSubmit)")
    public void noRepeat() {
    }

    @Around("noRepeat()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 线程安全地获取当前请求
        HttpServletRequest request = getCurrentRequest();
        if (request == null) {
            log.warn("无法获取当前HTTP请求，跳过防重复提交检查");
            return joinPoint.proceed();
        }

        // 获取方法和注解信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        NoRepeatSubmit annotation = method.getAnnotation(NoRepeatSubmit.class);
        
        if (annotation == null) {
            return joinPoint.proceed();
        }

        // 生成防重复提交的唯一键
        String repeatKey = generateRepeatKey(request, method, joinPoint.getArgs());
        
        // 检查是否重复提交
        if (isRepeatSubmit(repeatKey)) {
            throw new BaseKnownException(503, DEFAULT_ERROR_MESSAGE);
        }

        // 设置防重复提交标记
        setRepeatSubmitMark(repeatKey, annotation.value());
        
        try {
            // 执行目标方法
            Object result = joinPoint.proceed();
            return result;
        } catch (Exception e) {
            // 如果业务执行失败，可以选择清除防重复提交标记（根据业务需求）
            // 默认不清除，保持防重复提交的时效性
            log.debug("业务方法执行异常，保持防重复提交标记: {}", repeatKey);
            throw e;
        }
    }

    /**
     * 线程安全地获取当前HTTP请求
     */
    private HttpServletRequest getCurrentRequest() {
        try {
            ServletRequestAttributes attributes = 
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            return attributes.getRequest();
        } catch (IllegalStateException e) {
            log.debug("当前线程没有绑定HTTP请求上下文: {}", e.getMessage());
            return null;
        }
    }

    /**
     * 生成防重复提交的唯一键
     */
    private String generateRepeatKey(HttpServletRequest request, Method method, Object[] args) {
        // 获取会话ID，如果没有则使用IP地址
        String userIdentifier = getUserIdentifier(request);
        
        // 获取请求路径
        String requestPath = request.getServletPath();
        
        // 获取方法签名
        String methodSignature = method.getDeclaringClass().getSimpleName() + "." + method.getName();
        
        // 可选：加入参数哈希（针对相同路径不同参数的情况）
        int argsHash = args != null ? Arrays.hashCode(args) : 0;
        
        return REPEAT_SUBMIT_KEY_PREFIX + userIdentifier + ":" + 
               requestPath + ":" + methodSignature + ":" + argsHash;
    }

    /**
     * 获取用户标识符
     */
    private String getUserIdentifier(HttpServletRequest request) {
        // 优先使用Session ID
        String sessionId = request.getSession(false) != null ? 
            request.getSession().getId() : null;
        
        if (StringUtils.hasText(sessionId)) {
            return "session:" + sessionId;
        }
        
        // 备选方案：使用IP地址
        String clientIp = getClientIpAddress(request);
        return "ip:" + clientIp;
    }

    /**
     * 获取客户端真实IP地址
     */
    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (StringUtils.hasText(xForwardedFor) && !"unknown".equalsIgnoreCase(xForwardedFor)) {
            return xForwardedFor.split(",")[0].trim();
        }
        
        String xRealIp = request.getHeader("X-Real-IP");
        if (StringUtils.hasText(xRealIp) && !"unknown".equalsIgnoreCase(xRealIp)) {
            return xRealIp;
        }
        
        return request.getRemoteAddr();
    }

    /**
     * 检查是否重复提交
     */
    private boolean isRepeatSubmit(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        } catch (Exception e) {
            log.error("检查重复提交时Redis操作失败，key: {}", key, e);
            return false; // Redis异常时不阻止请求
        }
    }

    /**
     * 设置防重复提交标记
     */
    private void setRepeatSubmitMark(String key, long timeoutMs) {
        try {
            redisTemplate.opsForValue().set(key, "1", timeoutMs, TimeUnit.MILLISECONDS);
            log.debug("设置防重复提交标记: {}, 超时时间: {}ms", key, timeoutMs);
        } catch (Exception e) {
            log.error("设置防重复提交标记时Redis操作失败，key: {}", key, e);
        }
    }

    /**
     * 清除防重复提交标记（可选功能）
     */
    @SuppressWarnings("unused")
    private void clearRepeatSubmitMark(String key) {
        try {
            redisTemplate.delete(key);
            log.debug("清除防重复提交标记: {}", key);
        } catch (Exception e) {
            log.error("清除防重复提交标记时Redis操作失败，key: {}", key, e);
        }
    }




}
