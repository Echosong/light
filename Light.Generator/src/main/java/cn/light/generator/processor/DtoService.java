package cn.light.generator.processor;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.light.common.anno.AutoCover;
import cn.light.common.anno.AutoEntityField;
import cn.light.common.anno.InQueryDTO;
import cn.light.common.anno.NotinListDTO;
import cn.light.common.enums.BaseEnum;
import cn.light.common.enums.CodeTypeEnum;
import cn.light.common.enums.DtoTplEnum;
import cn.light.generator.config.Const;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.*;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>Title: </p >
 * <p>Description: 后端DTO自动生成</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0411:50</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Slf4j
public class DtoService extends BaseService implements ServiceInterface {
    DtoService(Class<?> clazz) {
        super(clazz);
    }

    private String dtoPath = "";

    /**
     * 业务开始处理
     */
    @Override
    public void start() {
        this.packageName = String.format(Const.SYS_PATH + ".{}.dto", Const.PACKET_PATH);

        String entityPackageName = Const.SYS_PATH + ".common";
        //新建文件夹
        dtoPath = super.getRealPath(entityPackageName) + "/" + StrUtil.lowerFirst(this.className);
        dtoPath = dtoPath.replace(".Common", StrUtil.format(".{}", StrUtil.upperFirst(Const.PACKET_PATH)))
                .replace("/common", StrUtil.format("/{}/dto", Const.PACKET_PATH));

        if (!FileUtil.exist(dtoPath)) {
            FileUtil.mkdir(dtoPath);
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        Class<?> superclass = clazz.getSuperclass();
        if(!Objects.equals(superclass.getSimpleName(), "SysBase")){
           declaredFields = ArrayUtil.addAll(declaredFields, superclass.getDeclaredFields());
        }
        this.writeDtoTpl(declaredFields, DtoTplEnum.QueryDTO);
        this.writeDtoTpl(declaredFields, DtoTplEnum.DTO);
        this.writeDtoTpl(declaredFields, DtoTplEnum.ListDTO);
    }


    /**
     * 模板处理函数
     *
     * @param declaredFields 模板字段
     * @param tplEnum 处理枚举
     */
    private void writeDtoTpl(Field[] declaredFields, DtoTplEnum tplEnum) {
        String dtoName = this.dtoPath + "/" + this.className + tplEnum.name() + ".java";
        if (FileUtil.isFile(dtoName)) {
            log.info("文件{}已经存在", dtoName);
            boolean autoCover = clazz.isAnnotationPresent(AutoCover.class);
            if(!autoCover){
                return;
            }
            AutoCover annotation = clazz.getAnnotation(AutoCover.class);

            if(tplEnum == DtoTplEnum.ListDTO &&!ArrayUtil.containsAny(annotation.value(), CodeTypeEnum.LIST_DTO,CodeTypeEnum.ALL) ){
                return;
            }
            if(tplEnum == DtoTplEnum.QueryDTO && !ArrayUtil.containsAny(annotation.value(), CodeTypeEnum.QUERY_DTO, CodeTypeEnum.ALL) ){
                return;
            }
            if(tplEnum == DtoTplEnum.DTO && !ArrayUtil.containsAny(annotation.value(), CodeTypeEnum.DTO, CodeTypeEnum.ALL) ){
                return;
            }
            //进行备份
            FileUtil.copy(dtoName, dtoName.replace(".java"
                            , StrUtil.format("_{}.txt", DateUtil.format(LocalDateTime.now(),"yyyMMddHHmmss")))
                    ,true);
        }
        String querDtoTpl =  tplEnum.name() + ".tpl";
        StringBuilder builder = new StringBuilder();
        Dict dict = this.replaceTpl(querDtoTpl);

        for (Field field : declaredFields) {
            if (!field.isAnnotationPresent(AutoEntityField.class)) {
                continue;
            }
            if (tplEnum == DtoTplEnum.QueryDTO && !field.isAnnotationPresent(InQueryDTO.class)) {
                continue;
            }
            if (tplEnum == DtoTplEnum.ListDTO && field.isAnnotationPresent(NotinListDTO.class)) {
                continue;
            }
            int loop = 1;
            if(tplEnum == DtoTplEnum.QueryDTO && "Date".equals(field.getType().getSimpleName())){
                loop = 2;
            }
            for (int i = 0; i< loop;i++) {
                AutoEntityField autoEntityField = field.getAnnotation(AutoEntityField.class);
                builder.append(this.validation(field));

                String typeString = field.getType().getSimpleName();
                String notes = "";

                if(autoEntityField.enums()  != BaseEnum.class){
                    notes = autoEntityField.enums().getSimpleName();
                }

                if(StrUtil.isNotEmpty(autoEntityField.notes())){
                    notes += autoEntityField.notes();
                }
                if(StrUtil.isNotBlank(notes)) {
                    notes = StrUtil.format(", description = \"{}\"", notes);
                }

                builder.append(StrUtil.format("    @Schema(title = \"{}\"{})", autoEntityField.value(),
                        notes));
                builder.append("\r\n");
                String autoEnumName = autoEntityField.enums().getSimpleName();

                //处理
                if (!"BaseEnum".equals(autoEnumName)) {
                    //这个地方要用全路径
                    builder.append(StrUtil.format("    @ApiModelPropertyEnum({}.class)", autoEnumName));
                    builder.append("\r\n");
                }
                String fileName  = field.getName();
                if(loop == 2){
                    fileName = "start"+StrUtil.upperFirst(field.getName());
                    if(i == 1){
                        fileName = "end"+ StrUtil.upperFirst(field.getName());
                    }
                }
                if("BigDecimal".equals(typeString)) {
                    dict.set("isBigDecimal", true);
                }
                builder.append(StrUtil.format("    private {} {};", typeString, fileName));
                builder.append("\r\n\r\n");
            }
        }

        dict.set("fields", builder.toString());

        var tpl = template.render(dict);
        FileUtil.writeString(tpl, dtoName, Charset.defaultCharset());
    }

    private StringBuilder validation(Field field) {
        StringBuilder validBuilder = new StringBuilder();
        if (field.isAnnotationPresent(NotNull.class)) {
            NotNull notNull = field.getAnnotation(NotNull.class);
            String message = "";
            message = StrUtil.format("(message = \"{}\")", notNull.message());
            validBuilder.append(StrUtil.format("    @NotNull{}", message));
            validBuilder.append("\r\n");
        }
        if (field.isAnnotationPresent(NotBlank.class)) {
            NotBlank notNull = field.getAnnotation(NotBlank.class);
            String message = StrUtil.format("(message = \"{}\")", notNull.message());

            validBuilder.append(StrUtil.format("    @NotBlank{}", message));
            validBuilder.append("\r\n");
        }
        if (field.isAnnotationPresent(Email.class)) {
            Email notNull = field.getAnnotation(Email.class);
            String message = StrUtil.format("(message = \"{}\")", notNull.message());
            validBuilder.append(StrUtil.format("    @Email{}", message));
            validBuilder.append("\r\n");
        }

        if (field.isAnnotationPresent(Pattern.class)) {
            String pattter = "    @Pattern({})";
            Pattern annotation = field.getAnnotation(Pattern.class);
            List<String> listAn = new ArrayList<>();
            listAn.add(" message = \"" + annotation.message() + "\"");
            if (StrUtil.isNotEmpty(annotation.regexp())) {
                String patternStr = annotation.regexp().replace("\\", "\\\\");
                listAn.add(" regexp = \"" + patternStr + "\"");
            }
            validBuilder.append(StrUtil.format(pattter, String.join(",", listAn)));
            validBuilder.append("\r\n");
        }

        if(field.isAnnotationPresent(Max.class)){
            var annotation = field.getAnnotation(Max.class);
            validBuilder.append(StrUtil.format("@Max(value={} message=\"{}\")", annotation.value(), annotation.message()));
            validBuilder.append("\r\n");
        }

        if(field.isAnnotationPresent(Min.class)){
            var annotation = field.getAnnotation(Min.class);
            validBuilder.append(StrUtil.format("@Min(value={} message=\"{}\")", annotation.value(), annotation.message()));
            validBuilder.append("\r\n");
        }

        if(field.isAnnotationPresent(DecimalMax.class)){
            var annotation = field.getAnnotation(DecimalMax.class);
            validBuilder.append(StrUtil.format("@DecimalMax(value={} message=\"{}\")", annotation.value(), annotation.message()));
            validBuilder.append("\r\n");
        }

        if(field.isAnnotationPresent(DecimalMin.class)){
            var annotation = field.getAnnotation(DecimalMin.class);
            validBuilder.append(StrUtil.format("@DecimalMin(value={} message=\"{}\")", annotation.value(), annotation.message()));
            validBuilder.append("\r\n");
        }

        if (field.isAnnotationPresent(Size.class)) {
            Size annotation = field.getAnnotation(Size.class);
            validBuilder.append(StrUtil.format("@Size(min={}, max={}, message=\"{}\")", annotation.min(), annotation.max(), annotation.message()));
            validBuilder.append("\r\n");
        }
        return validBuilder;
    }


}
