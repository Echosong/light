package com.kdao.autocode.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.kdao.autocode.Const;
import com.kdao.autocode.anno.AutoCover;
import com.kdao.autocode.anno.AutoEntityField;
import com.kdao.autocode.anno.InQueryDTO;
import com.kdao.autocode.enums.CodeTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Title: </p >
 * <p>Description: 后端Controller代码生成</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0411:49</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Slf4j
public class ControllerService extends BaseService implements ServiceInterface {

    ControllerService(Class<?> clazz){
        super(clazz);
    }

    /**
     * 业务处理开始
     */
    @Override
    public void start() {
        this.packageName = Const.SYS_PATH +".controller";

        String templateFile = this.templatePath+"controller.tpl";

        String tplPath = this.getRealPath(packageName);
        String fileName = tplPath +"/"+StrUtil.upperFirst(className)+"Controller.java";

        if(FileUtil.isFile(fileName)){
            log.warn(StrUtil.upperFirst(className)+"Controller文件已经存在!!!!!!!");
            boolean autoCover = clazz.isAnnotationPresent(AutoCover.class);
            if(!autoCover){
                return;
            }
            AutoCover annotation = clazz.getAnnotation(AutoCover.class);
            if( !ArrayUtil.contains(annotation.value(), CodeTypeEnum.CONTROLLER)){
                return;
            }
            //进行备份
            FileUtil.copy(fileName, fileName.replace("Controller.java"
                            , StrUtil.format("Controller_{}.txt", DateUtil.format(LocalDateTime.now(),"yyyMMddHHmmss")))
                            ,true);
        }
        String tplContent = this.replaceTpl(templateFile);
        tplContent = assignWhereCondition(tplContent);
        FileUtil.writeString(tplContent, fileName, Charset.defaultCharset());
    }

    /**
     * 根据queryDTO分配查询条件
     * @param tplContent 内容
     * @return 模板字符
     */
    private String  assignWhereCondition(String tplContent){
        Field[] fields = clazz.getDeclaredFields();

        List<String> fieldList = new ArrayList<>();
        for (Field field : fields) {
            if(!field.isAnnotationPresent(AutoEntityField.class)){
                continue;
            }
            if(!field.isAnnotationPresent(InQueryDTO.class)){
                continue;
            }
            if("Date".equals(field.getType().getSimpleName())){
                fieldList.add(StrUtil.format("queryDTO.getStart{}()", StrUtil.upperFirst(field.getName())));
                fieldList.add(StrUtil.format("queryDTO.getEnd{}()", StrUtil.upperFirst(field.getName())));
            }else {
                fieldList.add(StrUtil.format("queryDTO.get{}()", StrUtil.upperFirst(field.getName())));
            }
        }
        String queryParams = "";
        if(!fieldList.isEmpty()){
            queryParams =  String.join(",", fieldList) +", ";
        }

        tplContent = StrUtil.replace(tplContent, "#{queryParams}#", queryParams);
        return tplContent;
    }


}
