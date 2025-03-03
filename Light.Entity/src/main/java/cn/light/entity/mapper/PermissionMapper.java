package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.permission.PermissionQueryDTO;
import cn.light.entity.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 权限
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface PermissionMapper extends BaseMapper<SysPermission> {
    List<SysPermission> listPage(PermissionQueryDTO permissionQueryDTO);

    List<SysPermission> getListByUserId(Integer userId);
}
