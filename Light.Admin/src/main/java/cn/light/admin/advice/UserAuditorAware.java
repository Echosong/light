package cn.light.admin.advice;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * <p>Title: UserAuditorAware</p>
 * <p>Description: 通过使用jpa的audit功能。需要配置h-visions.audit.mode=jpa</p>
 * <p>Company: www.hn1024.cn</p>
 * <p>create date: 2020/4/26</p>
 *
 * @author :leiming
 * @version :1.0.0
 */
@Configuration
@Slf4j
@EnableJpaAuditing
public class UserAuditorAware implements AuditorAware<Integer> {

    /**
     * 获取当前用户信息
     * 如果token不是空，根据sessionId获取redis中的用户id,如果没有对应的header信息。或者其中出现了异常。用户id会被设置为0
     *
     * @return 当前用户信息
     */
    @Override
    public Optional<Integer> getCurrentAuditor() {
        try {
            return Optional.ofNullable(Convert.toInt(StpUtil.getLoginId()));
        }catch (Exception e) {
            return Optional.of(0);
        }
    }
}









