package cn.light.entity.cache;

import cn.light.packet.dto.permission.PermissionDTO;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

/**
 * UserPermissionCache
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 * @date : 2024/10/6 17:04
 */
@Getter
@Setter
@RedisHash
@AllArgsConstructor
public class UserPermissionCache {
    /**
     * 手机号
     */
    @Id
    private Integer id;

    /**
     * 短信内容
     */
    private List<PermissionDTO> permissions;
}
