package com.kdao.autocode.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import com.kdao.autocode.Const;
import com.kdao.autocode.anno.AutoCover;
import com.kdao.autocode.anno.AutoEntity;
import com.kdao.autocode.enums.CodeTypeEnum;
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


    public void getFile(String className){
        String repositoryName = className.substring(2)+"Repository";
        this.packageName = Const.SYS_PATH +".repository";
        String repositoryPath = super.getRealPath(packageName);

        String fileName = repositoryPath +"/"+ repositoryName +".java";
        if(FileUtil.isFile(fileName)){
            log.info("文件 {} 已经存在不需要从新生成，请确认，如果需要重新生成先删掉", fileName);
            boolean autoCover = clazz.isAnnotationPresent(AutoCover.class);
            if(!autoCover){
                return;
            }
            AutoCover annotation = clazz.getAnnotation(AutoCover.class);
            if( !annotation.value().equals(CodeTypeEnum.REPOSITORY)){
                return;
            }
            //进行备份
            FileUtil.copy(fileName, fileName.replace(".java"
                    , StrUtil.format("_{}.txt", DateUtil.format(LocalDateTime.now(),"yyyMMddHHmmss")))
                    ,true);
        }
        String templateFile = this.templatePath+"repositoryPage.tpl";
        //如果有dto 那么就需要生成分页，否则不需要
        if (clazz.isAnnotationPresent(AutoEntity.class)) {
            AutoEntity autoEntity = clazz.getAnnotation(AutoEntity.class);
            if(!autoEntity.dto()){
                templateFile = this.templatePath+"repository.tpl";
            }
        }
        String tplContent =  this.replaceTpl(templateFile);
        FileUtil.writeString(tplContent, fileName, Charset.defaultCharset());
        Console.log("生成Repository 文件 {} 成功 ", fileName);
    }


    /**
     * 业务处理入口
     */
    @Override
    public void start() {
        this.getFile(clazz.getSimpleName());
    }


}
