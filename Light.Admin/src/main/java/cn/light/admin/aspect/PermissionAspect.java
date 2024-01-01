package cn.light.admin.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 处理权限
 * @author : 二胡子
 * @version :1.0.0
 */
public class PermissionAspect {
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
        return joinPoint.proceed();
    }
}
