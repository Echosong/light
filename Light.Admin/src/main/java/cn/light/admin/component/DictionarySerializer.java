package cn.light.admin.component;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.light.common.annotation.ApiModelPropertyEnum;
import cn.light.common.component.IDictionaryObject;
import cn.light.common.dto.user.UserCacheDTO;
import cn.light.common.enums.BaseEnum;
import cn.light.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * <p>Title: </p >
 * <p>Description: 输出统一处理</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-10-3118:29</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Slf4j
public class DictionarySerializer extends JsonSerializer<IDictionaryObject> {

    private final UserService userService;

    public DictionarySerializer(StringRedisTemplate redisTemplate, UserService userService) {
        assert redisTemplate != null;
        this.userService = userService;
    }

    /**
     * 插入创建人，更新人
     *
     * @param fieldName 字段名
     * @return 用户插入
     */
    private Map<String, String> insertUserInfo(String fieldName, Integer value) {
        Map<String, String> map = new HashMap<>(2);
        List<String> userIds = Arrays.asList("creatorId", "updaterId");
        if (!userIds.contains(fieldName)) {
            return map;
        }
        //TODO 注意一定是内部操作系统才这么干，这里其实应该是后台操作人员，前端用户不要放这里
        List<UserCacheDTO> all = userService.getAllCache();
        all.stream().filter(t -> Objects.equals( t.getId(),value))
                .findFirst()
                .ifPresent(t -> {
                    String key = "creatorId".equals(fieldName) ? "creatorName" : "updaterName";
                    map.put("code", key);
                    map.put("value", t.getName());
                });
        return map;
    }


    @Override
    public void serialize(IDictionaryObject object, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        Field[] declaredFields = ReflectUtil.getFields(object.getClass());
        Set<String> methodNames = ReflectUtil.getMethodNames(object.getClass());
        for (String methodName : methodNames) {
            if(StrUtil.startWith(methodName, "get") && StrUtil.endWith(methodName, "Enum")
            && !"getEnum".equalsIgnoreCase(methodName)){
                jsonGenerator.writeObjectField(StrUtil.lowerFirst(StrUtil.removePrefixIgnoreCase(methodName,"get")),
                        ReflectUtil.invoke(object, methodName));
            }
        }
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(JsonIgnore.class)) {
                continue;
            }
            Object value = null;
            try {
                value = ReflectUtil.getFieldValue(object, field);
            } catch (Exception e) {
                log.warn("反射处理异常，请联系产品部门修复:设置对象属性失败：{}", field.getName());
            }
            Map<String, String> map = insertUserInfo(field.getName(), Convert.toInt(value, 0));
            if (!map.isEmpty()) {
                jsonGenerator.writeStringField(map.get("code"), map.get("value"));
            }
            if (field.isAnnotationPresent(ApiModelPropertyEnum.class)) {
                jsonGenerator.writeObjectField(field.getName(), value);
                ApiModelPropertyEnum annotation = field.getAnnotation(ApiModelPropertyEnum.class);
                Class<? extends BaseEnum> clazz = annotation.value();
                BaseEnum[] enums = clazz.getEnumConstants();
                for (BaseEnum e : enums) {
                    if (Objects.equals(Convert.toInt(value, 0), e.getCode())) {
                        jsonGenerator.writeStringField(field.getName() + "Enum", e.getName());
                    }
                }
            }
            jsonGenerator.writeObjectField(field.getName(), value);
        }
        jsonGenerator.writeEndObject();
    }


}
