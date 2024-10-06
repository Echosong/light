package cn.light.admin.aspect;

import cn.dev33.satoken.exception.SaTokenException;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.light.common.annotation.Permission;
import cn.light.common.consts.Consts;
import cn.light.common.exception.BaseKnownException;
import cn.light.packet.dto.permission.PermissionDTO;
import cn.light.server.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * 处理权限
 * @author : 二胡子
 * @version :1.0.0
 */
@Component
@Aspect
@Slf4j
public class PermissionAspect {
    @Resource
    private UserService userService;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(cn.light.common.annotation.Permission)")
    public void perPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法PerPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("perPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Permission permission = method.getAnnotation(Permission.class);

        boolean flg = true;
        String[] permissions = permission.permissions();
        //获取当前用户所有的权限码
        try {
            var userLogin = userService.getLoginInfo();
            //超级管理员不需要判断
            if(!Objects.equals(userLogin.getRoleCode(), Consts.ROLE_ADMIN_CODE)) {
                //判断权限操作
                if(ArrayUtil.isNotEmpty(permission.roles())) {
                    if (Arrays.stream(permission.roles()).noneMatch(role -> role.equals(userLogin.getRoleCode()))) {
                        throw new SaTokenException(permission.message());
                    }
                }
                var userPermissions = userLogin.getMenuList().stream().map(PermissionDTO::getPerms).toList();
                //进行权限判断
                if (ArrayUtil.isNotEmpty(permissions) && CollUtil.isNotEmpty(userPermissions)) {
                    flg = false;
                    for (String userPermission : userPermissions) {
                        if (ArrayUtil.contains(permissions, userPermission)) {
                            flg = true;
                            break;
                        }
                    }
                }
            }
        }catch (Exception ignored){}

        if(flg) {
            return joinPoint.proceed();
        }else{
            throw  new BaseKnownException(504, "无权限操作");
        }
    }
}
