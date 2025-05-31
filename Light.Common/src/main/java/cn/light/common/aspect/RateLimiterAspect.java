package cn.light.common.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.light.common.annotation.RateLimiter;
import cn.light.common.enums.LimitType;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.RedisUtils;
import cn.light.common.util.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.service.spi.ServiceException;
import org.redisson.api.RateType;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Method;

/**
 * 限流处理
 *
 * @author Lion Li
 */
@Slf4j
@Aspect
public class RateLimiterAspect {

    /**
     * 定义spel表达式解析器
     */
    private final ExpressionParser parser = new SpelExpressionParser();
    /**
     * 定义spel解析模版
     */
    private final ParserContext parserContext = new TemplateParserContext();
    /**
     * 方法参数解析器
     */
    private final ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();


    @Before("@annotation(rateLimiter)")
    public void doBefore(JoinPoint point, RateLimiter rateLimiter) {
        int time = rateLimiter.time();
        int count = rateLimiter.count();
        int timeout = rateLimiter.timeout();
        try {
            String combineKey = getCombineKey(rateLimiter, point);
            RateType rateType = RateType.OVERALL;
            if (rateLimiter.limitType() == LimitType.CLUSTER) {
                rateType = RateType.PER_CLIENT;
            }
            long number = RedisUtils.rateLimiter(combineKey, rateType, count, time, timeout);
            if (number == -1) {
                String message = rateLimiter.message();
                if (StrUtil.startWith(message, "{") && StrUtil.endWith(message, "}")) {
                    message = StrUtil.sub(message, 1, message.length() - 1);
                }
                throw new BaseKnownException(message);
            }
            log.info("限制令牌 => {}, 剩余令牌 => {}, 缓存key => '{}'", count, number, combineKey);
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                throw e;
            } else {
                throw new RuntimeException("服务器限流异常，请稍候再试", e);
            }
        }
    }

    private String getCombineKey(RateLimiter rateLimiter, JoinPoint point) {
        String key = rateLimiter.key();
        // 判断 key 不为空 和 不是表达式
        if (StrUtil.isNotBlank(key) && StrUtil.containsAny(key, "#")) {
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method targetMethod = signature.getMethod();
            Object[] args = point.getArgs();
            MethodBasedEvaluationContext context =
                new MethodBasedEvaluationContext(null, targetMethod, args, pnd);
            context.setBeanResolver(new BeanFactoryResolver(SpringUtil.getBeanFactory()));
            Expression expression;
            if (StrUtil.startWith(key, parserContext.getExpressionPrefix())
                && StrUtil.endWith(key, parserContext.getExpressionSuffix())) {
                expression = parser.parseExpression(key, parserContext);
            } else {
                expression = parser.parseExpression(key);
            }
            key = expression.getValue(context, String.class);
        }
        String RATE_LIMIT_KEY = "global_rate_limit:";
        StringBuilder stringBuffer = new StringBuilder(RATE_LIMIT_KEY);
        stringBuffer.append(ServletUtils.getRequest().getRequestURI()).append(":");
        if (rateLimiter.limitType() == LimitType.IP) {
            // 获取请求ip
            stringBuffer.append(ServletUtils.getClientIP()).append(":");
        } else if (rateLimiter.limitType() == LimitType.CLUSTER) {
            // 获取客户端实例id
            stringBuffer.append(RedisUtils.getClient().getId()).append(":");
        }
        return stringBuffer.append(key).toString();
    }
}
