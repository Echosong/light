package com.kdao.light.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SmUtil;
import com.kdao.light.common.dto.user.UserDTO;
import com.kdao.light.common.dto.user.UserQueryDTO;
import com.kdao.light.entity.KdUser;
import com.kdao.light.common.dto.user.LoginUserDTO;
import com.kdao.light.common.dto.user.UserCacheDTO;
import com.kdao.light.common.dto.user.UserDTO;
import com.kdao.light.common.dto.user.UserQueryDTO;
import com.kdao.light.common.enums.UserStateEnum;
import com.kdao.light.common.exception.BaseKnownException;
import com.kdao.light.common.utils.DtoMapper;
import com.kdao.light.entity.KdRole;
import com.kdao.light.entity.KdUser;
import com.kdao.light.entity.KdUserRole;
import com.kdao.light.repository.RoleRepository;
import com.kdao.light.repository.UserRepository;
import com.kdao.light.repository.UserRoleRepository;
import com.kdao.light.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>Title: 用户业务处理</p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002720:12</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
    }



    /**
     * 登录服务
     *
     * @param loginUserDTO 登录信息
     */
    @Override
    public SaTokenInfo login(LoginUserDTO loginUserDTO) {
        log.info("这里base64之前::"+loginUserDTO.getPassword() +"==="+loginUserDTO.getUsername());
        //前端密码简单用了base64处理了下
        loginUserDTO.setPassword(Base64.decodeStr(loginUserDTO.getPassword()));

        Optional<KdUser> byUsername = userRepository.findByUsername(loginUserDTO.getUsername());
        Assert.isTrue(byUsername.isPresent(), "账号或者密码错误");
        Assert.isTrue(Objects.equals(SmUtil.sm3().digestHex(loginUserDTO.getPassword()), byUsername.get().getPassword()),
                "账号或者密码错误"+ loginUserDTO.getPassword()+"====");
        Assert.isTrue(byUsername.get().getState().equals(UserStateEnum.ON.getCode()), "账号不允许登录！");

        StpUtil.login(byUsername.get().getId());
        byUsername.get().setLoginIp(loginUserDTO.getLoginIp());
        userRepository.save( byUsername.get());
        return StpUtil.getTokenInfo();
    }


    /**
     * 注册用户
     * @param user 用户数据
     */
    @CacheEvict(value = "Face_userAllCache", allEntries = true)
    @Override
    public void create(UserDTO user){
        //处理密码国密
        user.setPassword( SmUtil.sm3().digestHex(user.getPassword()));
        userRepository.findByUsername(user.getUsername()).ifPresent(t->{
            throw  new BaseKnownException(600, "账号已经存在，不能注册");
        });
        Assert.notNull(user.getRoleId(), "新增账号必须选择角色");
        KdUser save = userRepository.save(DtoMapper.convert(user, KdUser.class));
        KdUserRole kdUserRole = new KdUserRole();
        kdUserRole.setUserId(save.getId());
        kdUserRole.setRoleId(user.getRoleId());
        userRoleRepository.save(kdUserRole);
    }

    /**
     * 获取全部信息
     * @return 用户列表
     */
    @Cacheable(value = "Face_userAllCache")
    @Override
    public List<UserCacheDTO> getAllCache() {
        List<KdUser> all = userRepository.getAllByStateNot(UserStateEnum.DELETE.getCode());
        return DtoMapper.convertList(all, UserCacheDTO.class);
    }

    /**
     * 更新用户信息
     * @param userDTO 用户
     */
    @CacheEvict(value = "Face_userAllCache", allEntries = true)
    @Override
    public void update(UserDTO userDTO) {
        KdUser user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new BaseKnownException(300, "账号不存在"));
        user.setName(userDTO.getName());
        user.setState(userDTO.getState());
        user.setEmail(userDTO.getEmail());
        user.setSex(userDTO.getSex());

        //这里只考虑一个用户一个角色
        if (Objects.nonNull(userDTO.getRoleId())) {
            List<KdUserRole> allByUserId = userRoleRepository.findAllByUserId(user.getId());
            KdUserRole userRole = new KdUserRole();
            userRole.setRoleId(userDTO.getRoleId());
            userRole.setUserId(user.getId());
            if(!allByUserId.isEmpty()){
                Optional<KdUserRole> first = allByUserId.stream()
                        .filter(t -> t.getRoleId().equals(userDTO.getRoleId()))
                        .findFirst();
                if(!first.isPresent()){
                    //删掉老的
                    userRoleRepository.deleteAll(allByUserId);
                    userRoleRepository.save(userRole);
                }
            }else{
                //新增
                userRoleRepository.save(userRole);
            }
        }
        userRepository.save(user);
    }

    /**
     * 获取当前用户信息
     *
     * @return 用户信息
     */
    @Override
    public UserDTO getCurrent() {
        Integer userId = Convert.toInt(StpUtil.getLoginId());
        KdUser kdUser = userRepository.findById(userId)
                .orElseThrow(() -> new BaseKnownException(600, "用户不存在"));

        List<KdUserRole> userRoles = userRoleRepository.findAllByUserId(kdUser.getId());
        UserDTO userDTO = DtoMapper.convert(kdUser, UserDTO.class);
        if(CollUtil.isNotEmpty(userRoles)) {
            userDTO.setRoleId(userRoles.get(0).getRoleId());
        }
        return userDTO;
    }

    /**
     * 处理分页信息
     * @param userQueryDTO 用户信息
     * @return 用户信息
     */
    @Override
    @SuppressWarnings("All")
    public Page<UserDTO> list(UserQueryDTO userQueryDTO) {
        Page<KdUser> allByUsernameContaining = userRepository.findAllByUsernameContaining(userQueryDTO.getUsername(),
                userQueryDTO.getStartDate(),
                userQueryDTO.getEndDate(),
                userQueryDTO.getRequest());
        Page<UserDTO> userPage = DtoMapper.convertPage(allByUsernameContaining, UserDTO.class);

        //拿角色名称
        List<UserDTO> content = userPage.getContent();
        Set<Integer> userIds = content.stream().map(UserDTO::getId).collect(Collectors.toSet());
        List<KdUserRole> allByUserIdIn = userRoleRepository.findAllByUserIdIn(userIds);
        if(!allByUserIdIn.isEmpty()){
            List<Integer> roleIds = allByUserIdIn.stream().map(KdUserRole::getRoleId).collect(Collectors.toList());
            List<KdRole> allByIdIn = roleRepository.getAllByIdIn(roleIds);
            //先去到角色id
            content = content.stream().peek(t-> allByUserIdIn.stream().filter(k->k.getUserId().equals(t.getId()))
                    .findFirst()
                    .ifPresent(h-> t.setRoleId(h.getRoleId())))
                    .collect(Collectors.toList());
            //在取到角色名
            content.stream().peek(t-> allByIdIn.stream().filter(k-> Objects.equals(k.getId(),t.getRoleId()))
                    .findFirst()
                    .ifPresent(h-> t.setRoleName(h.getName())))
                    .collect(Collectors.toList());
        }
        return  userPage;
    }

    /**
     * 重置密码
     * @param password 密码
     * @param newPassword 密码
     */
    @Override
    public void setPassword(String password, String newPassword) {
        Integer userId = Convert.toInt( StpUtil.getLoginId(), 0);
        KdUser kdUser = userRepository.findById(userId).orElseThrow(() -> new BaseKnownException(600, "当前未登录"));
        Assert.isTrue(Objects.equals(SmUtil.sm3().digestHex(password), kdUser.getPassword()),
                "账号或者密码错误");
        kdUser.setPassword(SmUtil.sm3().digestHex(newPassword));
        userRepository.save(kdUser);
    }
}
