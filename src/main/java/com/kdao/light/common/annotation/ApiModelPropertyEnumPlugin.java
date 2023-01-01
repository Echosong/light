package com.kdao.light.common.annotation;

import com.kdao.light.common.enums.BaseEnum;
import org.springframework.core.annotation.AnnotationUtils;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.lang.reflect.AnnotatedElement;
import java.util.Optional;

import static springfox.documentation.schema.Annotations.findPropertyAnnotation;

/**
 * <p>Title: </p >
 * <p>Description: 自定义注解实现</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-10-3023:37</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
public class ApiModelPropertyEnumPlugin implements ModelPropertyBuilderPlugin {

    @Override
    public void apply(ModelPropertyContext context) {
        Optional<ApiModelPropertyEnum> enumOptional = Optional.empty();

        if (context.getAnnotatedElement().isPresent()) {
            enumOptional = findApiModePropertyAnnotation(context.getAnnotatedElement().get());
        }
        if (context.getBeanPropertyDefinition().isPresent()) {
            enumOptional = findPropertyAnnotation(context.getBeanPropertyDefinition().get(), ApiModelPropertyEnum.class);
        }

        if (enumOptional.isPresent()) {
            ApiModelPropertyEnum anEnum = enumOptional.get();
            String enumInfo = BaseEnum.getInfo(anEnum.value());
            context.getBuilder()
                    .required(enumOptional.get().required())
                    .description(anEnum.enumDesc() + ":" + enumInfo)
                    .example(enumOptional.get().example())
                    .isHidden(anEnum.hidden());
        }
    }
    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }

    public static Optional<ApiModelPropertyEnum> findApiModePropertyAnnotation(AnnotatedElement annotated) {
        ApiModelPropertyEnum annotation = AnnotationUtils.getAnnotation(annotated, ApiModelPropertyEnum.class);
        assert annotation != null;
        return Optional.of(annotation);
    }
}