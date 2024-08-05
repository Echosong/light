package cn.light.generator;

import cn.hutool.core.lang.Singleton;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.Setting;
import cn.light.common.consts.Consts;
import cn.light.generator.config.Config;
import cn.light.generator.config.Const;
import cn.light.generator.processor.DictionaryService;
import cn.light.generator.processor.InitService;

import java.util.Set;

/**
 * <p>Title: light</p >
 * <p>Description: Bootstrap</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2024/1/1 17:41 </p >
 *
 * @author : 二胡子
 * @version :1.0.0
 */
public class Bootstrap {
    public static void main(String[] args) {
        Config.initialization();



        Const.SYS_PATH = ClassUtil.getPackagePath(Bootstrap.class).replace("/", ".")
                .replace(".generator", "");
        if (StrUtil.isBlank(Const.ROOT_PATH)) {
            throw new RuntimeException("项目路路径");
        }
        //正式处理业务
        Set<Class<?>> classes = ClassUtil.scanPackage(Const.SYS_PATH + ".entity.entity");
        for (Class<?> aClass : classes) {
            InitService initService = Singleton.get(InitService.class);
            initService.run(aClass);
        }
        DictionaryService.writeDictionaryFile();
    }
}
