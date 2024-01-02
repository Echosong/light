package cn.light.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: light</p >
 * <p>Description: ReflectUtil</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2024/1/2 12:41 </p >
 *
 * @author : 二胡子
 * @version :1.0.0
 */

public class ReflectUtil {
    private static final int MAX_INHERIT_DEPTH = 5;

    public ReflectUtil() {
    }

    public static Field[] getAllFields(Class clazz) {
        List<Field> fieldList = new ArrayList();
        int inheritTimes = 0;

        Field[] fields;
        while(clazz != null && clazz != Object.class) {
            fields = clazz.getDeclaredFields();
            Field[] var4 = fields;
            int var5 = fields.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Field f = var4[var6];
                if (!fieldList.contains(f)) {
                    fieldList.add(f);
                    f.setAccessible(true);
                }
            }

            clazz = clazz.getSuperclass();
            ++inheritTimes;
            if (inheritTimes > 5) {
                break;
            }
        }

        fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }
}