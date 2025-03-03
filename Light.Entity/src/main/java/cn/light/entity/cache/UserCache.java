package cn.light.entity.cache;

import cn.light.common.anno.AutoEntityField;
import cn.light.entity.entity.SysUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

/**
 * 会员信息缓存
 * @author : 二胡子
 * @version :1.0.0
 */
@Getter
@Setter
@RedisHash(timeToLive = 60*60*24)
public class UserCache extends SysUser {
    @AutoEntityField(value = "token")
    private String token;

    @AutoEntityField(value = "角色编码")
    private String roleCode;

    @AutoEntityField(value = "角色名称")
    private String roleName;
}
