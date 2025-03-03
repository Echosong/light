package cn.light.server.service;


import cn.light.entity.entity.SysRole;
import cn.light.entity.mapper.RoleMapper;
import cn.light.packet.dto.role.RoleDTO;
import cn.light.packet.dto.role.RoleListDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

/**
 * 角色相关接口
 * @author : echosong
 * @version :1.0.0
 */
public interface RoleService extends IService<SysRole> {

    List<SysRole> listByUser(Integer userId);

    RoleDTO save(RoleDTO roleDTO);

    List<RoleListDTO> selectAll();

    void delete(@NotNull(message = "传入参数id不能为空") Integer id);

    List<Map<String, Object>> getMap();
}
