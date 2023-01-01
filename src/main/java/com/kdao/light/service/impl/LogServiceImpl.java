package com.kdao.light.service.impl;

import com.kdao.light.common.dto.log.LogDTO;
import com.kdao.light.common.utils.DtoMapper;
import com.kdao.light.entity.KdLog;
import com.kdao.light.entity.KdUser;
import com.kdao.light.repository.LogRepository;
import com.kdao.light.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        logRepository.save(DtoMapper.convert(logDTO, KdLog.class));
    }





}
