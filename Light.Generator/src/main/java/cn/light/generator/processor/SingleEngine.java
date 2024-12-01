package cn.light.generator.processor;

import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import cn.light.generator.config.Const;

import java.nio.charset.StandardCharsets;

/**
 * SingleEngine
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version :1.0.0
 * @date : 2024/12/1 14:11
 */
public class SingleEngine {
    private static volatile TemplateEngine instance;
    private SingleEngine(){}

    public static TemplateEngine getInstance(){
        if(instance == null){
            synchronized (SingleEngine.class){
                if(instance == null){
                    TemplateConfig templateConfig = new TemplateConfig(Const.TEMPLATE_PATH, TemplateConfig.ResourceMode.CLASSPATH);
                    templateConfig.setCharset(StandardCharsets.UTF_8);
                    instance = TemplateUtil.createEngine(templateConfig);
                }
            }
        }
        return instance;
    }
}
