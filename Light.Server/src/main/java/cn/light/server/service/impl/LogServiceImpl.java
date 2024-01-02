package cn.light.server.service.impl;


import cn.light.packet.dto.log.LogDTO;
import cn.light.common.util.DtoMapper;
import cn.light.entity.entity.KdLog;
import cn.light.entity.entity.KdUser;
import cn.light.entity.repository.LogRepository;
import jakarta.annotation.Resource;
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
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Resource
    private UserService userService;

    @Resource
    private  RedisTemplate<String, KdUser> redisTemplate;

    private final List<KdUser> userList = new ArrayList<>();


    @Override
    public void  setUserList(){
        for (int i = 0; i < 10; i++) {
            KdUser kdUser = new KdUser();
            kdUser.setId(i);
            userList.add(kdUser);
        }
    }

    @Override
    public List<KdUser> getUserList(){
        return  userList;
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
        //获取用户信息
        KdUser user = userService.getUserCache();
        String userName = Objects.isNull(user)?"未知用户": user.getUsername();
        logDTO.setUsername(userName);
        logRepository.save(DtoMapper.convert(logDTO, KdLog.class));
    }
}
