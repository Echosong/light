package cn.light.common.enums;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  枚举注意 code 不要 为0
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


    //反射获取某个包 以及子包下所有类名称
    static  List<String> getClassName(Class<?> clazz){
        List<String> list = new ArrayList<>();
        for (Class<?> c : clazz.getClasses()) {
            if (c.isEnum()) {
                list.add(c.getName());
                list.addAll(getClassName(c));
            }
        }
        return list;
    }


    public static boolean isClassExists(String packetClassName) {
        try {
            // 尝试通过类名获取 Class 对象
            Class<?> clazz = Class.forName(packetClassName);
            // 如果没有抛出异常，则类存在
            return true;
        } catch (ClassNotFoundException e) {
            // 如果捕获到 ClassNotFoundException，则类不存在
            return false;
        }
    }

    /**
     *
     * 根据简单得className 枚举 to MapList
     * TODO 这里如果每个业务自己有单独文件夹枚举，那么这里就不大对了，还需要前端能传相关包目录参数了
     * @return list
     * @throws ClassNotFoundException 信息
     */
    static  List<Map<String, Object>> toMap(String enumClass) throws ClassNotFoundException {
        String className = ClassUtil.getClassName(BaseEnum.class, false);
        Class<? extends BaseEnum> clazz;
        try {
            String packetClassName = className.replace("common.enums.BaseEnum", "packet.enums.system."+enumClass);
            if(!isClassExists(packetClassName)){
                packetClassName = className.replace("common.enums.BaseEnum", "packet.enums.business."+enumClass);
            }
            if(!isClassExists(packetClassName)){
                packetClassName = className.replace(".BaseEnum", "."+enumClass);
            }
            clazz = (Class<? extends BaseEnum>) Class.forName(packetClassName);
            ClassUtil.getLocationPath(BaseEnum.class);
        }catch (Exception e){
            throw new ClassNotFoundException("枚举类不存在");
        }
        return toMap(clazz);
    }
}
