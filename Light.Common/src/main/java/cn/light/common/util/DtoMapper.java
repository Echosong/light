package cn.light.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-10-3123:19</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Slf4j
public class DtoMapper {

    public DtoMapper() {
    }

    public static <T> T convert(Object obj, Class<T> clazz, String... ignoreProperty) {
        if (obj == null) {
            return null;
        } else {
            T t = BeanUtils.instantiateClass(clazz);
            BeanUtils.copyProperties(obj, t, ignoreProperty);
            return t;
        }
    }

    /** @deprecated */
    @Deprecated
    public static <IN, OUT> Page<OUT> convertPage(Page<IN> page, Pageable pageable, Class<OUT> clazz, String... ignoreProperty) {
        return new PageImpl((List)page.stream().map((t) -> {
            return convert(t, clazz, ignoreProperty);
        }).collect(Collectors.toList()), pageable, page.getTotalElements());
    }


    public static <IN, OUT> Page<OUT> convertPage(Page<IN> page, Function<IN, OUT> function) {
        Assert.notNull(page, "需要转换的分页数据不能为空");
        Assert.notNull(function, "转换方法不能为空");
        return new PageImpl((List)page.stream().map(function).collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }

    public static <IN, OUT> Page<OUT> convertPage(Page<IN> page, Class<OUT> clazz, String... ignoreProperty) {
        Assert.notNull(page, "需要转换的分页数据不能为空");
        Assert.notNull(clazz, "返回分页实体类型不能为空");
        return new PageImpl((List)page.stream().map((t) -> {
            return convert(t, clazz, ignoreProperty);
        }).collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }


    public static <IN, OUT> List<OUT> convertList(List<IN> list, Function<IN, OUT> function) {
        return (List)list.stream().map(function).collect(Collectors.toList());
    }

    public static <IN, OUT> List<OUT> convertList(List<IN> list, Class<OUT> clazz, String... ignoreProperty) {
        return (List)list.stream().map((t) -> {
            return convert(t, clazz, ignoreProperty);
        }).collect(Collectors.toList());
    }


}
