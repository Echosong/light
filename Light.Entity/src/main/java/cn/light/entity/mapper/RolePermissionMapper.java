package cn.light.entity.mapper;

import cn.light.entity.entity.SysRolePermission;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * RolePermissionMapper
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 * @date : 2025/1/17 21:28
 */
public interface RolePermissionMapper extends BaseMapper<SysRolePermission> {
    default List<SysRolePermission> getAllByRoleIdIn(List<Integer> roleIds){
        return this.selectList(new LambdaQueryWrapper<SysRolePermission>().in(SysRolePermission::getRoleId,roleIds));
    }
}
