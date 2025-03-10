package cn.light.entity.mapper;

import cn.light.entity.cache.UserCache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.user.UserQueryDTO;
import cn.light.entity.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * 自动生成 用户
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<SysUser> {
    List<SysUser> listPage(UserQueryDTO userQueryDTO);

    UserCache getUserCache(Integer id);

    default SysUser findByUsername(String username){
        return this.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
    }
}
