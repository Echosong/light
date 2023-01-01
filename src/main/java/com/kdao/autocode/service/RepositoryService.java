package com.kdao.autocode.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String templateFile = this.templatePath+"repository.tpl";
        String tplContent =  this.replaceTpl(templateFile);
        tplContent = assignWhereCondition(tplContent);
        FileUtil.writeString(tplContent, fileName, Charset.defaultCharset());
        Console.log("生成Repository 文件 {} 成功 ", fileName);
    }

    /**
     * 根据queryDTO分配查询条件 用jpql 要好些
     * @param tplContent 模板内容
     * @return 解析字符串
     */
    private String  assignWhereCondition(String tplContent){
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder whereBuilder = new StringBuilder(32);
        List<String> fieldList = new ArrayList<>();
        List<String> paramsList = new ArrayList<>();
        int index = 0;
        for (Field field : fields) {
            if(!field.isAnnotationPresent(AutoEntityField.class)){
                continue;
            }
            if(!field.isAnnotationPresent(InQueryDTO.class)){
                continue;
            }
            if("Date".equals(field.getType().getSimpleName())){
                fieldList.add(StrUtil.format("{} {}, {} {}", field.getType().getSimpleName(),
                        "start"+StrUtil.upperFirst(field.getName()),
                        field.getType().getSimpleName(),
                        "end"+StrUtil.upperFirst(field.getName())));
            }else {
                fieldList.add(StrUtil.format("{} {}", field.getType().getSimpleName(),
                        field.getName()));
            }
            AutoEntityField annotation = field.getAnnotation(AutoEntityField.class);
            paramsList.add(StrUtil.format("* @param {} {}", field.getName(),annotation.value()));
            index++;
            String whereString;
            Map<String, String> map = new HashMap<>(13);
            map.put("index", Convert.toStr(index));

            map.put("name", field.getName());

            if("String".equals(field.getType().getSimpleName())){
                whereString = StrUtil.format(" and (t1.{name} like CONCAT('%',?{index},'%')  or ?{index} is null or ?{index}='')", map);
            }else if("Date".equals(field.getType().getSimpleName())) {
                index = index+1;
                map.put("index1", Convert.toStr(index));
                whereString = StrUtil.format(" and (t1.{name}>?{index} or ?{index} is null) and (t1.{name}<=?{index} or ?{index} is null ",
                      map);
            }else{
                whereString = StrUtil.format(" and (t1.{name}=?{index} or ?{index} is null)", map);
            }
            whereBuilder.append(whereString);
        }
        tplContent = StrUtil.replace(tplContent, "#{queryWhere}#",
                StrUtil.format("where 1=1 {}", whereBuilder.toString()));
        String queryFields = "";
        if(!fieldList.isEmpty()){
            queryFields =  String.join(",", fieldList) +", ";
        }
        tplContent = StrUtil.replace(tplContent, "#{queryFields}#", queryFields);
        paramsList.add("* @param pageable 分页条件");
        tplContent = StrUtil.replace(tplContent, "#{pageable_params}#", String.join("\r\n     ", paramsList));
        return tplContent;
    }

    /**
     * 业务处理入口
     */
    @Override
    public void start() {
        this.getFile(clazz.getSimpleName());
    }
}
