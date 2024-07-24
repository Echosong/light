package cn.light.server.service.impl;


import cn.light.common.util.DtoMapper;
import cn.light.entity.entity.SysRole;
import cn.light.entity.entity.SysUserRole;
import cn.light.entity.mapper.RoleMapper;
import cn.light.entity.repository.RoleRepository;
import cn.light.entity.repository.UserRoleRepository;
import cn.light.packet.dto.role.RoleDTO;
import cn.light.packet.dto.role.RoleListDTO;
import cn.light.server.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 角色相关操作服务
 * @author : echosong
 * @version :1.0.0
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, SysRole> implements RoleService {
    @Resource
    private  UserRoleRepository userRoleRepository;
    @Resource
    private RoleRepository roleRepository;

    @Override
    public List<SysRole> listByUser(Integer userId) {
        List<SysUserRole> allByUserId = userRoleRepository.findAllByUserId(userId);
        List<Integer> roleIds = allByUserId.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        return this.baseMapper.selectBatchIds(roleIds);
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO){
        SysRole sysRole = DtoMapper.convert(roleDTO, SysRole.class);
        this.roleRepository.save(sysRole);
        return DtoMapper.convert(sysRole, RoleDTO.class);
    }

    @Override
    public List<RoleListDTO> selectAll(){
        return DtoMapper.convertList(this.list(), RoleListDTO.class);
    }

    @Override
    public void delete(@NotNull(message = "传入参数id不能为空") Integer id){
        List<SysUserRole> allByRoleId = userRoleRepository.findAllByRoleId(id);
        Assert.isTrue(allByRoleId.isEmpty(), "已经有用户归属改部门，所以不能删除");
        this.removeById(id);
    }

    @Override
    public List<Map<String, Object>> getMap(){
        List<SysRole> all = this.list(new LambdaQueryWrapper<SysRole>()
                .select(SysRole::getId, SysRole::getName)
                .orderByDesc(SysRole::getId)
        );
        List<Map<String, Object>> maps = new ArrayList<>();
        for (SysRole item : all) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("name", item.getName());
            maps.add(map);
        }
        return maps;
    }
}
