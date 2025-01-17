package cn.light.server.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SmUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.PageUtil;
import cn.light.entity.cache.SmsCache;
import cn.light.entity.cache.SmsCacheRepository;
import cn.light.entity.cache.UserCache;
import cn.light.entity.cache.UserCacheRepository;
import cn.light.entity.entity.SysRole;
import cn.light.entity.entity.SysUser;
import cn.light.entity.entity.SysUserRole;
import cn.light.entity.mapper.RoleMapper;
import cn.light.entity.mapper.UserMapper;
import cn.light.entity.mapper.UserRoleMapper;
import cn.light.packet.dto.user.*;
import cn.light.packet.enums.system.UserStateEnum;
import cn.light.server.service.PermissionService;
import cn.light.server.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 后台管理员相关账号处理
 * @author : echosong
 * @version :1.0.0
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {
    @Resource
    private  UserMapper userMapper;
    @Resource
    private UserCacheRepository userCacheRepository;
    @Resource
    private PermissionService permissionService;
    @Resource
    private SmsCacheRepository smsCacheRepository;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMapper roleMapper;

    /**
     * 登录服务
     *
     * @param loginUserDTO 登录信息
     */
    @Override
    public LoginResultDTO login(LoginUserDTO loginUserDTO) {
        SmsCache smsCache = smsCacheRepository.findById(loginUserDTO.getCodeUid())
                .orElseThrow(() -> new RuntimeException("验证码错误"));

        Assert.isTrue(smsCache.getContent().equalsIgnoreCase(loginUserDTO.getCode()), "验证码错误");
        //删除
        smsCacheRepository.deleteById(loginUserDTO.getCodeUid());
        //前端密码简单用了base64处理了下
        loginUserDTO.setPassword(Base64.decodeStr(loginUserDTO.getPassword()));

        var byUsername = this.baseMapper.findByUsername(loginUserDTO.getUsername());
        if(Objects.isNull(byUsername)){
            throw new RuntimeException("账号或者密码错误");
        }
        Assert.isTrue(Objects.equals(SmUtil.sm3().digestHex(loginUserDTO.getPassword()), byUsername.getPassword()),
                "账号或者密码错误"+ loginUserDTO.getPassword()+"====");
        Assert.isTrue(byUsername.getState().equals(UserStateEnum.ON.getCode()), "账号不允许登录！");

        StpUtil.login(byUsername.getId());
        byUsername.setLoginIp(loginUserDTO.getLoginIp());
        this.save( byUsername);
        userCacheRepository.deleteById(byUsername.getId());
        return getLoginInfo();
    }


    /**
     * 注册用户
     * @param user 用户数据
     */
    @CacheEvict(value = "Face_userAllCache", allEntries = true)
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(UserDTO user){
        //处理密码国密
        user.setPassword( SmUtil.sm3().digestHex(user.getPassword()));

        SysUser sysUser = this.baseMapper.findByUsername(user.getUsername());
        if(Objects.nonNull(sysUser)){
            throw new BaseKnownException(600, "账号已经存在，不能注册");
        }
        Assert.notNull(user.getRoleId(), "新增账号必须选择角色");
        SysUser save = DtoMapper.convert(user, SysUser.class);
        this.save(save);
        SysUserRole kdUserRole = new SysUserRole();
        kdUserRole.setUserId(save.getId());
        kdUserRole.setRoleId(user.getRoleId());
        userRoleMapper.insert(kdUserRole);
    }

    /**
     * 获取全部信息
     * @return 用户列表
     */
    @Override
    public List<UserCacheDTO> getAllCache() {
        List<UserCache> all = userCacheRepository.findAll();
        if(CollUtil.isEmpty(all)) {
            List<SysUser> list = this.lambdaQuery()
                    .ne(SysUser::getState, UserStateEnum.DELETE.getCode()).list();

            all = DtoMapper.convertList(list, UserCache.class);
             userCacheRepository.saveAll(all);
        }
        return DtoMapper.convertList(all, UserCacheDTO.class);
    }

    /**
     * 更新用户信息
     * @param userDTO 用户
     */
    @CacheEvict(value = "Face_userAllCache", allEntries = true)
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserDTO userDTO) {
        SysUser user = this.getOptById(userDTO.getId())
                .orElseThrow(() -> new BaseKnownException(300, "账号不存在"));
        user.setName(userDTO.getName());
        user.setState(userDTO.getState());
        user.setEmail(userDTO.getEmail());
        user.setSex(userDTO.getSex());
        user.setCode(userDTO.getCode());

        //这里只考虑一个用户一个角色
        if (Objects.nonNull(userDTO.getRoleId())) {
            List<SysUserRole> allByUserId = userRoleMapper.findAllByUserId(user.getId());
            SysUserRole userRole = new SysUserRole();
            userRole.setRoleId(userDTO.getRoleId());
            userRole.setUserId(user.getId());
            if(CollUtil.isNotEmpty(allByUserId)) {
                userRoleMapper.deleteBatchIds(allByUserId.stream().map(SysUserRole::getId).collect(Collectors.toList()));
            }
            userRoleMapper.insert(userRole);
        }
        this.saveOrUpdate(user);
    }

    @Override
    public UserCache getUserCache() {
        Integer userId = Convert.toInt(StpUtil.getLoginId(), 0);
        UserCache user = null;
        try {
            user = userCacheRepository.findById(userId).orElse(null);
        }catch (Exception ignored){}
        if (Objects.isNull(user)) {
            user = userMapper.getUserCache(userId);
            userCacheRepository.save(user);
        }
        return user;
    }


    /**
     * 处理分页信息
     * @param userQueryDTO 用户信息
     * @return 用户信息
     */
    @Override
    public Page<UserDTO> list(UserQueryDTO userQueryDTO) {
        Page<SysUser> allByUsernameContaining = PageUtil.getPage(userMapper::listPage, userQueryDTO);
        Page<UserDTO> userPage = DtoMapper.convertPage(allByUsernameContaining, UserDTO.class);

        //拿角色名称
        List<UserDTO> content = userPage.getContent();
        Set<Integer> userIds = content.stream().map(UserDTO::getId).collect(Collectors.toSet());
        List<SysUserRole> allByUserIdIn = userRoleMapper.findAllByUserIdIn(userIds);
        if(!allByUserIdIn.isEmpty()){
            List<Integer> roleIds = allByUserIdIn.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
            List<SysRole> allByIdIn = roleMapper.selectBatchIds(roleIds);

            for (UserDTO userDTO : content) {
                SysUserRole sysUserRole = allByUserIdIn.stream().filter(k -> k.getUserId().equals(userDTO.getId()))
                        .findFirst().orElse(null);
                if (Objects.nonNull(sysUserRole)) {
                    userDTO.setRoleId(sysUserRole.getRoleId());
                }
                SysRole sysRole = allByIdIn.stream().filter(k -> k.getId().equals(userDTO.getRoleId()))
                        .findFirst().orElse(null);
                if (Objects.nonNull(sysRole)) {
                    userDTO.setRoleName(sysRole.getName());
                    userDTO.setRoleCode(sysRole.getCode());
                }
            }
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
        SysUser kdUser = this.getOptById(userId).orElseThrow(() -> new BaseKnownException(600, "当前未登录"));
        Assert.isTrue(Objects.equals(SmUtil.sm3().digestHex(password), kdUser.getPassword()),
                "账号或者密码错误");
        kdUser.setPassword(SmUtil.sm3().digestHex(newPassword));
        this.updateById(kdUser);
    }

    @Override
    public List<Map<String, Object>> getMap() {
        List<SysUser> all = this.baseMapper.selectList(new LambdaQueryWrapper<SysUser>()
                .select(SysUser::getId, SysUser::getName)
                .orderByDesc(SysUser::getId)
        );
        List<Map<String, Object>> maps = new ArrayList<>();
        for (SysUser item : all) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("name", item.getName());
            maps.add(map);
        }
        return maps;
    }

    @Override
    public LoginUserDTO captcha() {
        var gifCaptcha = CaptchaUtil.createCircleCaptcha(120, 50);
        String code = gifCaptcha.getCode();
        String codeUid = UUID.randomUUID().toString();
        var smsCache = SmsCache.builder().id(codeUid).content(code).build();
        smsCacheRepository.save(smsCache);
        LoginUserDTO loginUser = new LoginUserDTO();
        loginUser.setCode(gifCaptcha.getImageBase64Data());
        loginUser.setCodeUid(codeUid);
        //创建一个文件流 转base64
        return loginUser;
    }

    @Override
    public UserDTO find(Integer userId) {
        return DtoMapper.convert(this.baseMapper.selectById(userId), UserDTO.class);
    }

    @Override
    public void resetPassword(Integer userId, String defaultPassword) {
        this.getOptById(userId).ifPresent(t -> {
            t.setPassword(SmUtil.sm3().digestHex(defaultPassword));
            this.updateById(t);
        });
    }

    @Override
    public LoginResultDTO getLoginInfo() {
        UserCache userCache = this.getUserCache();
        LoginResultDTO resultDTO = DtoMapper.convert(userCache, LoginResultDTO.class);
        resultDTO.setToken(StpUtil.getTokenInfo().getTokenValue());
        resultDTO.setMenuList( permissionService.listByUser(userCache.getId()));
        return resultDTO;
    }

    @Override
    public void currentUpdate(UserDTO userDTO) {
        UserCache userCache = this.getUserCache();
        this.lambdaUpdate()
                .set(SysUser::getInfo,userDTO.getInfo())
                .set(SysUser::getName, userDTO.getName())
                .set(SysUser::getEmail,userDTO.getEmail())
                .set(SysUser::getSex,userDTO.getSex())
                .eq(SysUser::getId,userCache.getId())
                .update();
        userCacheRepository.deleteById(userCache.getId());
    }

    @Override
    public void delete(Integer userId) {
        this.removeById(userId);
        userRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>()
                .eq(SysUserRole::getUserId, userId)
        );
    }
}
