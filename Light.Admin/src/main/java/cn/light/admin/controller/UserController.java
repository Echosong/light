package cn.light.admin.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.light.common.annotation.Log;
import cn.light.common.annotation.NoPermission;
import cn.light.common.annotation.NoRepeatSubmit;
import cn.light.common.annotation.Permission;
import cn.light.common.consts.Consts;
import cn.light.common.enums.BusinessEnum;
import cn.light.common.enums.LogicalEnum;
import cn.light.common.util.DtoMapper;
import cn.light.entity.entity.SysUser;
import cn.light.packet.dto.user.*;
import cn.light.packet.enums.system.UserStateEnum;
import cn.light.server.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 用户管理
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "用户 控制器")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    @Resource
    private  UserService userService;

    @Value("123456")
    private String defaultPassword;


    @PostMapping("/create")
    @Operation(summary = "新增用户")
    @Log("新增用户")
    public void create(@RequestBody @Valid UserDTO user) {
        user.setRegIp(getRemoteIp());
        userService.create(user);
    }


    @Operation(summary = "获取当前用户信息")
    @GetMapping("/getLoginInfo")
    public LoginResultDTO getLoginInfo() {
        return  userService.getLoginInfo();
    }


    @PutMapping("/listPage")
    @Operation(summary = "分页查询用户")
    public Page<UserListDTO> list(@RequestBody UserQueryDTO userQueryDTO) {
        return  DtoMapper.convertPage(userService.list(userQueryDTO), UserListDTO.class);
    }


    @PostMapping(path = {"/currentUpdate"})
    @Operation(summary = "更新用户")
    @Log("更新用户")
    public void currentUpdate(@RequestBody UserDTO userDTO) {
        userService.currentUpdate(userDTO);
    }


    @PostMapping("/save")
    @Operation(summary = "更新用户")
    @Log("更新用户")
    public void update(@RequestBody UserDTO userDTO) {
        if (Objects.isNull(userDTO.getId())) {
            userDTO.setRegIp(getRemoteIp());
            userService.create(userDTO);
        } else {
            userService.update(userDTO);
        }
    }

    @Operation(summary = "获取所有用户")
    @GetMapping("/all")
    public List<UserKeyDTO> all() {
        List<SysUser> all = userService.list(new LambdaQueryWrapper<SysUser>()
                .ne(SysUser::getState, UserStateEnum.DELETE.getCode())
        );
        return DtoMapper.convertList(all, UserKeyDTO.class);
    }

    @PostMapping("/setPassword")
    @Operation(summary = "重置设置密码")
    @Log("重置设置密码")
    public void setPassword(@RequestBody Map<String, String> map) {
        String password = map.get("password");
        String newPassword = map.get("newPassword");
        String rePassword = map.get("rePassword");
        Assert.isTrue(newPassword.equals(rePassword), "两次输入密码不一致");
        userService.setPassword(password, newPassword);
    }

    @GetMapping("/resetPassword/{userId}")
    @Operation(summary = "管理员重置密码")
    @Log("管理员重置密码")
    public void resetPassword(@PathVariable(value = "userId") Integer userId) {
        userService.resetPassword(userId, this.defaultPassword);

    }

    /**
     * 登录
     *
     * @param loginUserDTO 登录
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录")
    @Log(value = "用户登录", businessType = BusinessEnum.LOGIN)
    @NoRepeatSubmit(value = 1000)
    public LoginResultDTO login(@RequestBody @Valid LoginUserDTO loginUserDTO) {
        loginUserDTO.setLoginIp(getRemoteIp());
        return userService.login(loginUserDTO);
    }

    /**
     * 退出登录
     */
    @Operation(summary = "强制退出登录")
    @GetMapping("/logout")
    @Log(value = "退出登录",  businessType = BusinessEnum.LOGIN)
    public void logout() {
        StpUtil.logout(StpUtil.getLoginId());
    }

    /**
     * 删除，账号
     *
     * @param userId 用户id
     */
    @DeleteMapping("/delete/{userId}")
    @Operation(summary = "删除")
    @Permission(logical = LogicalEnum.OR, permissions = Consts.ROLE_ADMIN_CODE)
    @Log("删除用户")
    public void delete(@PathVariable(value = "userId") Integer userId) {
        userService.delete(userId);
    }

    @Operation(summary = "获取某个用户")
    @GetMapping("/find/{userId}")
    public UserDTO find(@PathVariable(value = "userId") Integer userId) {
        return userService.find(userId);
    }


    @Operation(summary = "验证码")
    @GetMapping("/captcha")
    @NoPermission
    public LoginUserDTO captcha(){
        return userService.captcha();
    }

    @Operation(summary = "简单处理活运营商")
    @GetMapping("/getMap")
    public List<Map<String, Object>> getMap(){
        return userService.getMap();
    }

}