package cn.light.entity.cache;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

/**
 * 发送短信缓存
 * @author : 二胡子
 * @version :1.0.0
 */
@RedisHash(timeToLive = 60*5)
@Getter
@Setter
@Builder
public class SmsCache {
    /**
     * 手机号
     */
    @Id
    private String id;

    /**
     * 短信内容
     */
    private String content;
}
