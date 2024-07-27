package cn.light.admin.controller;

import cn.light.entity.entity.SysLog;
import cn.light.entity.mapper.LogMapper;
import cn.light.entity.repository.LogRepository;
import cn.light.server.service.LogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * logTest
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 * @date : 2024/7/27 14:39
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("hz")
public class LogTest {
    @Autowired
    private LogRepository logRepository ;
    @Resource
    private LogMapper logMapper;
    @Test
    public void saveLog(){
        SysLog sysLog = new SysLog();
        sysLog.setId(39);
        sysLog.setAddress("xxxxxxxxxxxxxx");
        logMapper.updateById(sysLog);

        //证明  了@DynamicInsert和@DynamicUpdate 只对 Mpper有用
    }
}
