package com.kdao.light.advice;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer loginId = Convert.toInt(StpUtil.getLoginId());
        return true;
    }
}
