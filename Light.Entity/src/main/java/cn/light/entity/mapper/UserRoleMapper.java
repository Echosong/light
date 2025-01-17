package cn.light.entity.mapper;

import cn.light.entity.entity.SysUserRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Set;

/**
 * UserRoleMapper
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 * @date : 2025/1/17 21:09
 */
public interface UserRoleMapper extends BaseMapper<SysUserRole> {
    default List<SysUserRole> findAllByUserId(Integer userId){
        return this.selectList(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId,userId));
    }

    default List<SysUserRole> findAllByUserIdIn(Set<Integer> userIds){
        return this.selectList(new LambdaQueryWrapper<SysUserRole>().in(SysUserRole::getUserId,userIds));
    }

    default List<SysUserRole> findAllByRoleId(Integer roleId){
        return this.selectList(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getRoleId,roleId));
    }
}
