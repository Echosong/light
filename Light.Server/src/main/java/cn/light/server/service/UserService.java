package cn.light.server.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.light.entity.cache.UserCache;
import cn.light.packet.dto.user.*;
import cn.light.entity.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002720:11</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
public interface UserService extends IService<SysUser> {
    /**
     * 登录服务
     * @param loginUserDTO
     */
    LoginResultDTO login(LoginUserDTO loginUserDTO);

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


    UserCache getUserCache();

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

    List<Map<String, Object>> getMap();

    LoginUserDTO captcha();

    UserDTO find(Integer userId);

    void resetPassword(Integer userId, String defaultPassword);

    LoginResultDTO getLoginInfo();

    void currentUpdate(UserDTO userDTO);

    void delete(Integer userId);
}
