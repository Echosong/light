package com.kdao.light.advice;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.extra.spring.SpringUtil;
import com.kdao.light.common.annotation.NoPermission;
import com.kdao.light.common.exception.BaseKnownException;
import com.kdao.light.entity.KdUser;
import com.kdao.light.repository.UserRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * <p>Title: liveShop</p >
 * <p>Description: LoginInterceptor</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2023-02-06 22:14 </p >
 *
 * @author : 二胡子
 * @version :1.0.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    private final RedisTemplate<String, KdUser> redisTemplate;

    public LoginInterceptor(RedisTemplate<String, KdUser> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod) handler;
            NoPermission methodAnnotation = h.getMethodAnnotation(NoPermission.class);
            //不需要做权限判断
            if(Objects.nonNull(methodAnnotation)){
                return true;
            }
            String userId = StpUtil.getLoginId().toString();
            if (redisTemplate.opsForValue().get(userId) == null) {
                UserRepository userRepository = SpringUtil.getBean("userRepository");
                KdUser user = userRepository.findById(Integer.parseInt(userId)).orElseThrow(() -> new BaseKnownException("用户不存在"));
                redisTemplate.opsForValue().set(userId, user);
            }
        }
        return true;
    }
}
