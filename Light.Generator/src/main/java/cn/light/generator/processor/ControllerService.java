package cn.light.generator.processor;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.light.common.anno.AutoCover;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.enums.CodeTypeEnum;
import cn.light.generator.Const;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    ControllerService(Class<?> clazz) {
        super(clazz);
    }

    /**
     * 业务处理开始
     */
    @Override
    public void start() {
        this.packageName = Const.SYS_PATH + StrUtil.format(".{}.controller", Const.ADMIN_PATH);

        String templateFile = this.templatePath + "controller.tpl";

        //这里因为运行时没有Admin 的
        String entityPackageName = Const.SYS_PATH + ".common";
        String tplPath = this.getRealPath(entityPackageName);
        //路径进一步处理
        tplPath = tplPath.replace(".Common", StrUtil.format(".{}", StrUtil.upperFirst(Const.ADMIN_PATH)))
                .replace("/common", StrUtil.format("/{}/controller", Const.ADMIN_PATH));
        String fileName = tplPath + "/" + StrUtil.upperFirst(className) + "Controller.java";

        if (FileUtil.isFile(fileName)) {

            Console.log(StrUtil.upperFirst(className) + "Controller文件已经存在!!!!!!!");
            boolean autoCover = clazz.isAnnotationPresent(AutoCover.class);
            if (!autoCover) {
                return;
            }
            AutoCover annotation = clazz.getAnnotation(AutoCover.class);
            if (!ArrayUtil.contains(annotation.value(), CodeTypeEnum.CONTROLLER)) {
                return;
            }
            //进行备份
            FileUtil.copy(fileName, fileName.replace("Controller.java"
                            , StrUtil.format("Controller_{}.txt", DateUtil.format(LocalDateTime.now(), "yyyMMddHHmmss")))
                    , true);
        }
        String tplContent = this.replaceTpl(templateFile);
        tplContent = assignWhereCondition(tplContent);
        FileUtil.writeString(tplContent, fileName, Charset.defaultCharset());
    }

    /**
     * 根据queryDTO分配查询条件
     *
     * @param tplContent 内容
     * @return 模板字符
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
                fieldList.add(StrUtil.format("queryDTO.getStart{}()", StrUtil.upperFirst(field.getName())));
                fieldList.add(StrUtil.format("queryDTO.getEnd{}()", StrUtil.upperFirst(field.getName())));
            } else {
                fieldList.add(StrUtil.format("queryDTO.get{}()", StrUtil.upperFirst(field.getName())));
            }
        }
        String queryParams = "";
        if (!fieldList.isEmpty()) {
            queryParams = String.join(",", fieldList) + ", ";
        }

        tplContent = StrUtil.replace(tplContent, "#{queryParams}#", queryParams);
        return tplContent;
    }


}
