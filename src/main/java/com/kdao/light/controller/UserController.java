package com.kdao.light.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SmUtil;
import com.kdao.light.common.annotation.Log;
import com.kdao.light.common.annotation.NoRepeatSubmit;
import com.kdao.light.common.dto.file.FileDTO;
import com.kdao.light.common.dto.file.FileQueryDTO;
import com.kdao.light.common.dto.user.*;
import com.kdao.light.common.enums.UserStateEnum;
import com.kdao.light.common.utils.DtoMapper;
import com.kdao.light.entity.KdFile;
import com.kdao.light.entity.KdUser;
import com.kdao.light.repository.UserRepository;
import com.kdao.light.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 *用户操作
 * @author : echosong
 * @version :1.0.0
 */
@RestController
@RequestMapping("/user")
@Tag(name = "用户操作")
@Validated
public class UserController extends BaseController {
    private final UserRepository userRepository;
    private final UserService userService;


    @Value("k-dao.password: 123456")
    private String defaultPassword;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    /**
     * 分页获取用户
     *
     * @param user 用户json
     */
    @PostMapping("/create")
    @Operation(summary = "新增用户")
    @Log("新增用户")
    public void create(@RequestBody @Valid UserDTO user) {
        user.setRegIp(getRemoteIp());
        userService.create(user);
    }

    /**
     * 获取当前的用户
     *
     */
    @Operation(summary = "获取当前用户")
    @GetMapping("/getCurrent")
    public UserDTO getCurrent() {
        return  userService.getCurrent();
    }




    /**
     * 分页查询用户
     *
     * @param userQueryDTO 分页查询条件
     */
    @PutMapping("/listPage")
    @Operation(summary = "分页查询用户")
    public Page<UserListDTO> list(@RequestBody UserQueryDTO userQueryDTO) {
        return  DtoMapper.convertPage(userService.list(userQueryDTO), UserListDTO.class);
    }

    /**
     * 更新用户
     */
    @PostMapping(path = {"/update","/save"})
    @Operation(summary = "更新用户")
    @Log("更新用户")
    public void update(@RequestBody UserDTO userDTO) {
        Integer userId = userDTO.getId();
        if (Objects.isNull(userId)) {
            userId = Convert.toInt(StpUtil.getLoginId(), 0);
        }
        userDTO.setId(userId);
        userService.update(userDTO);
    }

    /**
     * 获取所有用户信息
     *

     */
    @Operation(summary = "获取所有用户")
    @GetMapping("/all")
    public List<UserKeyDTO> all() {
        List<KdUser> all = userRepository.getAllByStateNot(UserStateEnum.DELETE.getCode());
        return DtoMapper.convertList(all, UserKeyDTO.class);
    }

    /**
     * 设置密码
     */
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

    /**
     * 后台管理员重置密码
     *
     * @param userId 用户
     */
    @GetMapping("/resetPassword/{userId}")
    @Operation(summary = "管理员重置密码")
    @Log("管理员重置密码")
    public void resetPassword(@PathVariable Integer userId) {
        userRepository.findById(userId).ifPresent(t -> {
            t.setPassword(SmUtil.sm3().digestHex(this.defaultPassword));
            userRepository.save(t);
        });
    }

    /**
     * 登录
     *
     * @param loginUserDTO 登录
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录")
    @Log("用户登录")
    @NoRepeatSubmit(value = 1000)
    public SaTokenInfo login(@RequestBody @Valid LoginUserDTO loginUserDTO) {
        loginUserDTO.setLoginIp(getRemoteIp());
        return userService.login(loginUserDTO);
    }

    /**
     * 退出登录
     */
    @Operation(summary = "强制退出登录")
    @GetMapping("/logout")
    @Log("退出登录")
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
    @Log("删除用户")
    public void delete(@PathVariable Integer userId) {
        userRepository.findById(userId).ifPresent(t -> {
            t.setState(UserStateEnum.DELETE.getCode());
            userRepository.save(t);
        });
    }

    /**
     * 定义图形验证码的长和宽
     */
    private final LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100,4,10);

    /**
     * 验证码
     */
    @Operation(summary = "验证码")
    @GetMapping("/captcha")
    public void captcha(HttpServletResponse response) throws IOException {

        lineCaptcha.write(response.getOutputStream());
        response.getOutputStream().close();
    }


}
