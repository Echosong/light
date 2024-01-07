package cn.light.server.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.light.packet.dto.user.LoginUserDTO;
import cn.light.packet.dto.user.UserCacheDTO;
import cn.light.packet.dto.user.UserDTO;
import cn.light.packet.dto.user.UserQueryDTO;
import cn.light.entity.entity.SysUser;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002720:11</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
public interface UserService {
    /**
     * 登录服务
     * @param loginUserDTO
     */
    SaTokenInfo login(LoginUserDTO loginUserDTO);

    /**
     * 注册用户
     * @param user
     */
    void create(UserDTO user);

    /**
     * 获取所有用户缓存下来
     * @return
     */
    List<UserCacheDTO> getAllCache();

    /**
     * 更新用户信息
     * @param userDTO
     */
    void update(UserDTO userDTO);

    /**
     * 获取当前用户信息
     * @return 用户信息
     */
    UserDTO getCurrent();


    SysUser getUserCache();

    /**
     * 处理分页信息
     * @param userQueryDTO
     * @return
     */
    Page<UserDTO> list(UserQueryDTO userQueryDTO);

    /**
     * 充值密码
     * @param password
     * @param newPassword
     */
    void setPassword(String password, String newPassword);
}
