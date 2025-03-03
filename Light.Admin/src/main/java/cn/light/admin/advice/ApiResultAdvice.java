package cn.light.admin.advice;

import cn.light.common.annotation.ApiResultIgnore;
import cn.light.common.exception.BaseErrorCode;
import cn.light.common.util.ResultVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

/**
 * <p>Title: 统一处理返回</p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002720:33</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@ControllerAdvice
public class ApiResultAdvice implements ResponseBodyAdvice {

    public ApiResultAdvice(){

    }

    private final ThreadLocal<ObjectMapper> mapperThreadLocal = ThreadLocal.withInitial(ObjectMapper::new);
    private static final Class[] ANNOS = new Class[]{RequestMapping.class, GetMapping.class, PostMapping.class, DeleteMapping.class, PutMapping.class};
    private static final Class IGNORE = ApiResultIgnore.class;

    @SuppressWarnings("unchecked")
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        AnnotatedElement element = returnType.getAnnotatedElement();
        //只有不包含face的不再包装
        if (!returnType.getContainingClass().toString().contains("light")) {
            return false;
        } else {
            return !element.isAnnotationPresent(IGNORE) && Arrays.stream(ANNOS).anyMatch((annoy) -> annoy.isAnnotation() && element.isAnnotationPresent(annoy));
        }
    }


    @Override
    public Object beforeBodyWrite(Object returnValue, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ObjectMapper mapper = this.mapperThreadLocal.get();
        if (returnValue instanceof String) {
            try {
                Object strObject = mapper.writeValueAsString(ResultVO.success(returnValue));
                response.getHeaders().setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));
                return strObject;
            } catch (JsonProcessingException var9) {
                var9.printStackTrace();
            }
        }
        if(returnValue instanceof BaseErrorCode){
            return returnValue;
        }
        return ResultVO.success(returnValue);
    }
}
