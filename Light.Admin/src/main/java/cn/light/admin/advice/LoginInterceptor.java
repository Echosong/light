package cn.light.admin.advice;

import cn.hutool.extra.spring.SpringUtil;
import cn.light.common.annotation.NoPermission;
import cn.light.entity.entity.SysUser;
import cn.light.server.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod) handler;
            NoPermission methodAnnotation = h.getMethodAnnotation(NoPermission.class);
            //不需要做权限判断
            if(Objects.nonNull(methodAnnotation)){
                return true;
            }
            SysUser user = SpringUtil.getBean(UserService.class).getUserCache();
            return  true;
        }
        return true;
    }
}
