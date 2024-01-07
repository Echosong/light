package cn.light.server.service.impl;


import cn.light.packet.dto.log.LogDTO;
import cn.light.common.util.DtoMapper;
import cn.light.entity.entity.SysLog;
import cn.light.entity.entity.SysUser;
import cn.light.entity.repository.LogRepository;
import cn.light.server.service.LogService;
import cn.light.server.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>Title: </p >
 * <p>Description: 日志信息处理</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2022-11-10 21:55</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Service
@Slf4j
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, SysUser> redisTemplate;

    private final List<SysUser> userList = new ArrayList<>();


    @Override
    public void setUserList() {
        for (int i = 0; i < 10; i++) {
            SysUser kdUser = new SysUser();
            kdUser.setId(i);
            userList.add(kdUser);
        }
    }

    @Override
    public List<SysUser> getUserList() {
        return userList;
    }

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    /**
     * 记录日志
     *
     * @param logDTO 日志
     */
    @Override
    public void save(LogDTO logDTO) {
        String userName = null;
        //获取用户信息
        try {
            SysUser user = userService.getUserCache();
            userName = Objects.isNull(user) ? "未知用户" : user.getUsername();
        } catch (Exception ignored) {
            log.warn("获取用户信息失败");
        }
        logDTO.setUsername(userName);
        logRepository.save(DtoMapper.convert(logDTO, SysLog.class));
    }
}
