package com.kdao.autocode.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
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
import java.util.List;

/**
 * 获取mapper.xml
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@Slf4j
public class MapperService extends BaseService implements ServiceInterface {
    MapperService(Class<?> clazz) {
        super(clazz);
    }

    public void getMapperFile(String className) {
        this.packageName = Const.SYS_PATH + ".mapper";
        String repositoryName = className.substring(2);
        String tplPath = this.getRealPath(packageName);
        String fileName = tplPath + "/" + repositoryName + "Mapper.java";
        if (FileUtil.isFile(fileName)) {
            Console.log(StrUtil.format("文件{}已存在，无需重新生成", fileName));
            return;
        }
        String templateFile = this.templatePath + "mapper.tpl";
        String tplContent = this.replaceTpl(templateFile);
        FileUtil.writeString(tplContent, fileName, Charset.defaultCharset());
        Console.log("生成Mapper 文件 {} 成功 ", fileName);
    }


    public void getFile(String className) {
        String repositoryName = className.substring(2) + "Mapper";
        this.packageName = Const.SYS_PATH + ".mapper";
        String repositoryPath = Const.ROOT_PATH + "/src/main/resources/mapper/";

        String fileName = repositoryPath + repositoryName + ".xml";
        if (FileUtil.isFile(fileName)) {
            boolean autoCover = clazz.isAnnotationPresent(AutoCover.class);
            if (!autoCover) {
                return;
            }
            AutoCover annotation = clazz.getAnnotation(AutoCover.class);

            if (!ArrayUtil.contains(annotation.value(), CodeTypeEnum.MAPPER)) {
                return;
            }
            //进行备份
            FileUtil.copy(fileName, fileName.replace(".xml"
                            , StrUtil.format("_{}.txt", DateUtil.format(LocalDateTime.now(), "yyyMMddHHmmss")))
                    , true);
        }
        String templateFile = this.templatePath + "mapperXml.tpl";
        String tplContent = this.replaceTpl(templateFile);
        tplContent = assignWhereCondition(tplContent);
        FileUtil.writeString(tplContent, fileName, Charset.defaultCharset());
        Console.log("生成Mapper.xml 文件 {} 成功 ", fileName);
    }

    /**
     * 根据queryDTO分配查询条件 用jpql 要好些
     *
     * @param tplContent 模板内容
     * @return 解析字符串
     */
    private String assignWhereCondition(String tplContent) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> fieldList = new ArrayList<>();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(AutoEntityField.class)) {
                continue;
            }
            if (!field.isAnnotationPresent(InQueryDTO.class)) {
                continue;
            }
            if ("Date".equals(field.getType().getSimpleName())) {
                fieldList.add(StrUtil.format("<if test=\"{} != null \"> \n and {} &gt; #{{}} \n </if>",
                        "start" + StrUtil.upperFirst(field.getName()),
                        StrUtil.toUnderlineCase(field.getName()).toLowerCase(),
                        "start" + StrUtil.upperFirst(field.getName())
                ));

                fieldList.add(StrUtil.format("<if test=\"{} != null \">\n and {}  &lt; #{{}} \n</if>",
                        "end" + StrUtil.upperFirst(field.getName()),
                        StrUtil.toUnderlineCase(field.getName()).toLowerCase(),
                        "end" + StrUtil.upperFirst(field.getName())
                ));
            } else if ("String".equals(field.getType().getSimpleName())) {
                fieldList.add(StrUtil.format("<if test=\"{} != null \">\n and {}  like CONCAT('%',#{{}},'%')\n </if>",
                        field.getName(),
                        StrUtil.toUnderlineCase(field.getName()).toLowerCase(),
                        field.getName()
                ));
            } else {
                fieldList.add(StrUtil.format("<if test=\"{} != null \"> \n and {} = #{{}} \n </if>",
                        field.getName(),
                        StrUtil.toUnderlineCase(field.getName()).toLowerCase(),
                        field.getName()
                ));
            }
        }
        String queryFields = "";
        if (!fieldList.isEmpty()) {
            queryFields = String.join("\n", fieldList);
        }
        tplContent = StrUtil.replace(tplContent, "#{sql_where}#", queryFields);
        return tplContent;
    }

    /**
     * 业务处理入口
     */
    @Override
    public void start() {
        this.getMapperFile(clazz.getSimpleName());
        this.getFile(clazz.getSimpleName());
    }
}
