package cn.light.entity.cache;

import cn.light.entity.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * 用缓存框架
 * @author hz
 */
public interface UserCacheRepository extends JpaRepository<UserCache, Integer> {
}
