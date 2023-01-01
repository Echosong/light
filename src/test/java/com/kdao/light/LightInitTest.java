package com.kdao.light;

import cn.hutool.core.lang.Singleton;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.setting.Setting;
import com.kdao.autocode.Const;
import com.kdao.autocode.service.InitService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LightInitTest {

    @Value("${spring.datasource.url}")
    private String dataUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${auto-config.vue-root-path}")
    private String vueRootPath;

    @Value("${auto-config.sys-path}")
    private String sysPath;

    private void initSetting() {
        log.info("初始化项目开始");
        Setting setting = new Setting("db.setting");
        setting.set("url", this.dataUrl);
        setting.set("user", this.username);
        setting.set("pass", this.password);
        setting.store();
        log.info("设置db.setting 文件成功");
        //配置基础路径
        Const.SYS_PATH = sysPath;
        Const.VUE_PATH = vueRootPath + Const.VUE_PATH;
        Const.VUE_ROOT_ROUTER = vueRootPath + Const.VUE_ROOT_ROUTER;
        log.info("设置基础配置参数成功");
    }

    @Test
    void autoApplication() {
        this.initSetting();
        Set<Class<?>> classes = ClassUtil.scanPackage(Const.SYS_PATH + ".entity");
        for (Class<?> aClass : classes) {
            InitService initService = Singleton.get(InitService.class);
            initService.run(aClass);
        }
    }

}
