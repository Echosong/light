package  cn.light.server.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.light.entity.entity.SysUserRole;
import cn.light.entity.mapper.UserRoleMapper;
import cn.light.entity.repository.UserRoleRepository;
import cn.light.server.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 自动生成 用户角色 service 实现
 * @author : 二胡子
 * @date : 2024-07-17 23:06:09
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, SysUserRole> implements UserRoleService {
        @Resource
        private  UserRoleRepository userRoleRepository;

}