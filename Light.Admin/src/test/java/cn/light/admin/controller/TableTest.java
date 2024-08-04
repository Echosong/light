package cn.light.admin.controller;

import cn.light.server.service.TableColumnService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TableTest
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 * @date : 2024/8/3 8:53
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("hz")
public class TableTest {
    @Resource
    private TableColumnService tableColumnService;

    @Test
    public void test() {
        tableColumnService.initUserTable();
    }
}
