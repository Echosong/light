package cn.light.generator.processor;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.light.common.anno.AutoCover;
import cn.light.common.anno.AutoEntity;
import cn.light.common.enums.CodeTypeEnum;
import cn.light.generator.Const;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

/**
 * <p>Title: </p >
 * <p>Description: 生成Repository</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0412:34</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Slf4j
public class RepositoryService extends  BaseService implements ServiceInterface{

    RepositoryService(Class<?> clazz){
        super(clazz);
    }


    public void getFile(){
        String repositoryName = className+"Repository";
        String repositoryPath = super.getRealPath(packageName)+"/repository";

        String fileName = repositoryPath +"/"+ repositoryName +".java";
        if(FileUtil.isFile(fileName)){
            log.info("文件 {} 已经存在不需要从新生成，请确认，如果需要重新生成先删掉", fileName);
            boolean autoCover = clazz.isAnnotationPresent(AutoCover.class);
            if(!autoCover){
                return;
            }
            AutoCover annotation = clazz.getAnnotation(AutoCover.class);
            if( !ArrayUtil.contains(annotation.value(), CodeTypeEnum.REPOSITORY)){
                return;
            }
            //进行备份
            FileUtil.copy(fileName, fileName.replace(".java"
                    , StrUtil.format("_{}.txt", DateUtil.format(LocalDateTime.now(),"yyyMMddHHmmss")))
                    ,true);
        }
        String templateFile = this.templatePath+"repository.tpl";

        String tplContent =  this.replaceTpl(templateFile);
        FileUtil.writeString(tplContent, fileName, Charset.defaultCharset());
        Console.log("生成Repository 文件 {} 成功 ", fileName);
    }


    /**
     * 业务处理入口
     */
    @Override
    public void start() {
        this.packageName = Const.SYS_PATH +".entity";
        this.getFile();
    }


}
