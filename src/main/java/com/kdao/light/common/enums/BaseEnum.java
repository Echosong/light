package com.kdao.light.common.enums;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: </p >
 * <p>Description: 枚举接口，用来处理api文档</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-10-3023:00</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
public interface BaseEnum {
   
    //接口方法
    Integer getCode();
    String getName();

    /**
     * 返回枚举类的说明
     *
     * @param clazz 枚举类类对象
     * @return 信息
     */
    static String getInfo(Class<? extends BaseEnum> clazz) {
        BaseEnum[] enums = clazz.getEnumConstants();
        Map<Integer, String> map = new HashMap<>(enums.length);
        for (BaseEnum e : enums) {
            map.put(e.getCode(), e.getName());
        }
        return JSONUtil.toJsonPrettyStr(map);
    }

    /**
     * 转换成map 方便前端输出
     * @param clazz 类
     * @return list
     */
    static  List<Map<String, Object>> toMap(Class<? extends  BaseEnum> clazz){
        BaseEnum[] enums = clazz.getEnumConstants();
        List<Map<String, Object>> maps = new ArrayList<>(enums.length);
        for (BaseEnum e : enums) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("code", e.getCode());
            map.put("name", e.getName());
            maps.add(map);
        }
        return maps;
    }

    /**
     * 根据简单得className 枚举 to MapList
     * @return list
     * @throws ClassNotFoundException 信息
     */
    static  List<Map<String, Object>> toMap(String enumClass) throws ClassNotFoundException {
        String className = ClassUtil.getClassName(BaseEnum.class, false)
                .replace(".BaseEnum", "."+enumClass);
        Class<? extends BaseEnum> clazz = (Class<? extends BaseEnum>) Class.forName(className);
        ClassUtil.getLocationPath(BaseEnum.class);
        return toMap(clazz);
    }
}
