package cn.light.generator.processor;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.light.common.anno.AutoCover;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.enums.CodeTypeEnum;
import cn.light.generator.config.Const;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void getMapperFile() {
        String repositoryName = this.className;
        String tplPath = this.getRealPath(packageName)+"/mapper";
        String fileName = tplPath + "/" + repositoryName + "Mapper.java";
        if (FileUtil.isFile(fileName)) {
            boolean autoCover = clazz.isAnnotationPresent(AutoCover.class);
            if (!autoCover) {
                return;
            }
            AutoCover annotation = clazz.getAnnotation(AutoCover.class);

            if (!ArrayUtil.containsAny(annotation.value(), CodeTypeEnum.MAPPER, CodeTypeEnum.ALL)) {
                return;
            }

            FileUtil.copy(fileName, fileName.replace(".java"
                            , StrUtil.format("_{}.txt", DateUtil.format(LocalDateTime.now(), "yyyMMddHHmmss")))
                    , true);
        }

        Dict dict = this.replaceTpl("mapper.tpl");
        String tplContent = template.render(dict);

        FileUtil.writeString(tplContent, fileName, Charset.defaultCharset());
        Console.log("生成Mapper.java 文件 {} 成功 ", fileName);
    }


    public void getFile() {
        String repositoryName = this.className + "Mapper";
        String repositoryPath = Const.ROOT_PATH + "/Light.Entity/src/main/resources/mapper/";
        String fileName = repositoryPath + repositoryName + ".xml";

        if (FileUtil.isFile(fileName)) {

            boolean autoCover = clazz.isAnnotationPresent(AutoCover.class);
            if (!autoCover) {
                return;
            }
            AutoCover annotation = clazz.getAnnotation(AutoCover.class);

            if (!ArrayUtil.contains(annotation.value(), CodeTypeEnum.MAPPER_XML)) {
                return;
            }
            FileUtil.copy(fileName, fileName.replace(".xml"
                            , StrUtil.format("_{}.txt", DateUtil.format(LocalDateTime.now(), "yyyMMddHHmmss")))
                    , true);

            //进行备份
            Console.log("生成Mapper.xml 文件 {} 已经存在 ", fileName);
        }

        Dict dict = this.replaceTpl("mapperXml.tpl");
        dict.set("sql_where", assignWhereCondition());
        String tplContent = template.render(dict);

        FileUtil.writeString(tplContent, fileName, Charset.defaultCharset());
        Console.log("生成Mapper.xml 文件 {} 生成成功 ", fileName);
    }

    /**
     * 根据queryDTO分配查询条件 用jpql 要好些
     *
     * @return 解析字符串
     */
    private String assignWhereCondition() {
        Field[] fields = clazz.getDeclaredFields();
        List<String> fieldList = new ArrayList<>();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(AutoEntityField.class)) {
                continue;
            }
            if (!field.isAnnotationPresent(InQueryDTO.class)) {
                continue;
            }
            Map<String, String> fieldMap = new HashMap<>();
            fieldMap.put("name", field.getName());
            fieldMap.put("underlineName",  StrUtil.toUnderlineCase(field.getName()).toLowerCase());

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
                fieldList.add(StrUtil.format("<if test=\"{name} != null and {name} != '' \">\n and {underlineName}  like CONCAT('%',#{{name}},'%')\n </if>",
                        fieldMap
                ));
            } else {
                fieldList.add(StrUtil.format("<if test=\"{name} != null \"> \n and {underlineName} = #{{name}} \n </if>",
                        fieldMap
                ));
            }
        }
        String queryFields = "";
        if (!fieldList.isEmpty()) {
            queryFields = String.join("\n", fieldList);
        }

        return queryFields;

    }

    /**
     * 业务处理入口
     */
    @Override
    public void start() {
        this.packageName = Const.SYS_PATH + ".entity";
        this.getMapperFile();
        this.getFile();
    }
}
