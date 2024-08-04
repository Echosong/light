package cn.light.admin.controller;

import cn.light.entity.entity.SysLog;
import cn.light.entity.mapper.LogMapper;
import cn.light.entity.repository.LogRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * DbTest
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 * @date : 2024/7/31 14:05
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("hz")
public class DbTest {
    @Resource
    private LogMapper mapper;
    @Resource
    private LogRepository logRepository;

    //两次查询用不同的session
    @Test
    public void test() {
        Optional<SysLog> logOp = logRepository.findById(1);

        if(logOp.isEmpty()){
            return;
        }
        log.info("处理之前jpa查询"+ logOp.get().getAddress());

        logOp.get().setAddress("bbbbb");

        mapper.updateById(logOp.get());

        SysLog sysLog = mapper.selectById(1);

        log.info("更新之后mapper查询"+  sysLog.getAddress());
    }

    //两次查询用同一个session
    @Test
    public void test1() {
        Optional<SysLog> logOp = logRepository.findById(1);

        if(logOp.isEmpty()){
            return;
        }
        log.info("处理之前jpa查询"+ logOp.get().getAddress());

        logOp.get().setAddress("cccccccccccccc");

        mapper.updateById(logOp.get());


        Optional<SysLog> logOp2 = logRepository.findById(1);

        log.info("更新之后mapper查询"+  logOp2.get().getAddress());
    }

    // jap 操作
    @Test
    public void test2() {
        Optional<SysLog> logOp = logRepository.findById(1);

        if(logOp.isEmpty()){
            return;
        }
        log.info("处理之前jpa查询"+ logOp.get().getAddress());

        logOp.get().setAddress("ddddddddddddddddd");

        logRepository.save(logOp.get());


        var logOp2 = mapper.selectById(1);

        log.info("更新之后mapper查询"+  logOp2.getAddress());
    }


    @Test
    @Transactional(rollbackFor = Exception.class)
    public void test3() {
         var logOp = mapper.selectById(1);


        log.info("处理之前jpa查询"+ logOp.getAddress());

        logOp.setAddress("HHHHHHHHHHHHHHHH");

        logRepository.save(logOp);


        var logOp2 = mapper.selectById(1);

        log.info("更新之后mapper查询"+  logOp2.getAddress());
    }
}
