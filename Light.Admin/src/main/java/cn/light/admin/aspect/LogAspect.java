/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package cn.light.admin.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.light.common.annotation.Log;
import cn.light.entity.cache.UserCache;
import cn.light.entity.entity.SysUser;
import cn.light.packet.dto.log.LogDTO;
import cn.light.packet.enums.system.YesOrNoEnum;
import cn.light.server.service.LogService;
import cn.light.server.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * <p>Title: 日志处理</p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002720:33</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    private final LogService logService;

    private final HttpServletRequest request;

    private final UserService userService;



    @Autowired
    public LogAspect(LogService logService, UserService userService, HttpServletRequest request) {
        this.logService = logService;
        this.request = request;
        this.userService = userService;
    }


    /**
     * 配置切入点
     */
    @Pointcut("@annotation(cn.light.common.annotation.Log)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime =  System.currentTimeMillis();
        Object result = null;
        String exceptionMessage = "";
        String userName = "";
        //如果退出登录用户要先获取要不然执行以后获取用户就不存在了
        try {
            userName = Optional.ofNullable(userService.getUserCache()).map(UserCache::getUsername).orElse("");
        }catch (Exception ignored){}

        try {
            result = joinPoint.proceed();
        }catch (Exception e){
            exceptionMessage = e.getMessage();
            throw  e;
        }finally {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Log aopLog = method.getAnnotation(Log.class);
            // 方法路径
            String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
            LogDTO logDTO = new LogDTO();
            logDTO.setLogType(aopLog.businessType().getCode());
            logDTO.setRequestIp(getIp(request));
            logDTO.setBrowser(request.getHeader("User-Agent"));
            logDTO.setDescription(aopLog.value());
            logDTO.setExceptionDetail(exceptionMessage);
            if(StrUtil.isBlank(exceptionMessage)){
                logDTO.setState(YesOrNoEnum.ON.getCode());
            }else{
                logDTO.setState(YesOrNoEnum.OFF.getCode());
            }
            logDTO.setUsername(userName);
            logDTO.setTime(System.currentTimeMillis() - startTime);
            logDTO.setParams(getParameter(method, joinPoint.getArgs()));
            logDTO.setMethod(methodName);
            logDTO.setUrlPath(request.getRequestURI());
            logService.save(logDTO);
        }
        return result;
    }
    public  String getIp(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private String getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>(12);
                String key = parameters[i].getName();
                if (!StrUtil.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.isEmpty()) {
            return "";
        }
        return argList.size() == 1 ? JSONUtil.toJsonStr(argList.get(0)) : JSONUtil.toJsonStr(argList);
    }


}
