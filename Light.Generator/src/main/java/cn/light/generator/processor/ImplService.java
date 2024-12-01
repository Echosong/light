package cn.light.generator.processor;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.light.common.anno.AutoCover;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.enums.CodeTypeEnum;
import cn.light.generator.config.Const;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

/**
 * 处理 service 相关生成
 * @author : 二胡子
 * @version :1.0.0
 */
@Slf4j
public class ImplService  extends BaseService implements ServiceInterface  {
    ImplService(Class<?> clazz) {
        super(clazz);
    }

    @Override
    public void start() {
        //生成接口文件
        getFile("");
        //生成实现类
        getFile("impl");
    }

    public void getFile(String implStr){

        this.packageName = Const.SYS_PATH + StrUtil.format(".{}", Const.SERVICE_PATH);

        String entityPackageName = Const.SYS_PATH + ".common";
        String tplPath = this.getRealPath(entityPackageName);

        tplPath = tplPath.replace(".Common", StrUtil.format(".{}", StrUtil.upperFirst(Const.SERVICE_PATH)))
                .replace("/common", StrUtil.format("/{}/service"+(StrUtil.isNotBlank(implStr)?"/"+implStr:""), Const.SERVICE_PATH));
        String fileName = tplPath + "/" + StrUtil.upperFirst(className) + "Service"+StrUtil.upperFirst(implStr)+".java";

        if(FileUtil.isFile(fileName)){
            log.info("文件 {} 已经存在不需要从新生成，请确认，如果需要重新生成先删掉", fileName);
            boolean autoCover = clazz.isAnnotationPresent(AutoCover.class);
            if(!autoCover){
                return;
            }
            AutoCover annotation = clazz.getAnnotation(AutoCover.class);
            if( !ArrayUtil.containsAny(annotation.value(), CodeTypeEnum.SERVICE, CodeTypeEnum.ALL)){
                return;
            }
            //进行备份
            FileUtil.copy(fileName, fileName.replace(".java"
                            , StrUtil.format("_{}.txt", DateUtil.format(LocalDateTime.now(),"yyyMMddHHmmss")))
                    ,true);
        }

        Field[] fields = clazz.getDeclaredFields();
        this.keyName = "";
        for (Field field : fields) {
            if (!field.isAnnotationPresent(AutoEntityField.class)) {
                continue;
            }
            AutoEntityField annotation = field.getAnnotation(AutoEntityField.class);
            if (annotation.isKeyName()) {
                keyName = StrUtil.upperFirst(field.getName());
            }
        }
        String templateFile = this.templatePath+"implInterface.tpl";

        if(StrUtil.isNotBlank(implStr)){
            templateFile = this.templatePath+"implService.tpl";
        }

        Dict dict = this.replaceTpl(templateFile);

        String tplContent =  template.render(dict);


        FileUtil.writeString(tplContent, fileName, Charset.defaultCharset());
        Console.log("生成Service 文件 {} 成功 ", fileName);
    }
}
