package cn.light.common.util;

import cn.light.common.dto.ExtendColumnInfo;
import cn.light.common.enums.ExtendColumnTypeEnum;
import cn.light.common.exception.BaseKnownException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Title: light</p >
 * <p>Description: StringObjectMapper</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2024/1/2 12:20 </p >
 *
 * @author : 二胡子
 * @version :1.0.0
 */

public class StringObjectMapper {
    private static final Logger log = LoggerFactory.getLogger(StringObjectMapper.class);
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat SIMPLE_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public StringObjectMapper() {
    }

    static Object convertStringToObject(Field field, String cellString) {
        cellString = cellString.trim();
        Class cls = field.getType();
        if (cls.equals(String.class)) {
            return cellString;
        } else if (!cls.equals(Date.class) && !cls.equals(java.sql.Date.class)) {
            if (cls.equals(Time.class)) {
                try {
                    return Time.valueOf(cellString);
                } catch (IllegalArgumentException var4) {
                    log.warn("数据转换异常:{}", var4.getMessage());
                    return null;
                }
            } else if (!cls.equals(Float.TYPE) && !cls.equals(Float.class)) {
                if (!cls.equals(Integer.class) && !cls.equals(Integer.TYPE)) {
                    if (cls.equals(BigDecimal.class)) {
                        try {
                            return new BigDecimal(cellString);
                        } catch (NumberFormatException var5) {
                            return new BigDecimal("0");
                        }
                    } else if (!cls.equals(Double.class) && !cls.equals(Double.TYPE)) {
                        if (!cls.equals(Boolean.TYPE) && !cls.equals(Boolean.class)) {
                            throw new BaseKnownException(500, "");
                        } else {
                            return Boolean.valueOf(cellString);
                        }
                    } else {
                        try {
                            return  Double.parseDouble(cellString);
                        } catch (NumberFormatException var6) {
                            return  Double.parseDouble("0");
                        }
                    }
                } else {
                    try {
                        return Integer.parseInt(cellString);
                    } catch (NumberFormatException var7) {
                        return 0;
                    }
                }
            } else {
                try {
                    return Float.parseFloat(cellString);
                } catch (NumberFormatException var8) {
                    return Float.parseFloat("0");
                }
            }
        } else {
            return parseDateTime(cellString);
        }
    }

    private static Object parseDateTime(String cellString) {
        try {
            return SIMPLE_DATE_TIME_FORMAT.parse(cellString);
        } catch (ParseException var4) {
            try {
                return SIMPLE_DATE_FORMAT.parse(cellString);
            } catch (ParseException var3) {
                return null;
            }
        }
    }

    static Object getExtendObject(String cellString, ExtendColumnInfo cellPropertyExtendInfo) {
        String typeName = cellPropertyExtendInfo.getColumnType().toUpperCase();
        if (typeName.equals(ExtendColumnTypeEnum.VARCHAR.toString())) {
            return cellString;
        } else if (typeName.equals(ExtendColumnTypeEnum.DATE.toString())) {
            try {
                return SIMPLE_DATE_FORMAT.parse(cellString);
            } catch (ParseException var4) {
                log.warn("数据列：{},数据转换异常{}", typeName, var4.getMessage());
                return null;
            }
        } else if (typeName.equals(ExtendColumnTypeEnum.DATETIME.toString())) {
            try {
                return SIMPLE_DATE_TIME_FORMAT.parse(cellString);
            } catch (ParseException var5) {
                log.warn("数据列：{},数据转换异常{}", typeName, var5.getMessage());
                return null;
            }
        } else if (typeName.equals(ExtendColumnTypeEnum.FLOAT.toString())) {
            try {
                return Float.parseFloat(cellString);
            } catch (NumberFormatException var6) {
                log.warn("数据列：{},数据转换异常{}", typeName, var6.getMessage());
                return Float.parseFloat("0");
            }
        } else if (typeName.equals(ExtendColumnTypeEnum.INT.toString())) {
            try {
                return Integer.parseInt(cellString);
            } catch (NumberFormatException var7) {
                log.warn("数据列：{},数据转换异常{}", typeName, var7.getMessage());
                return 0;
            }
        } else if (typeName.equals(ExtendColumnTypeEnum.DECIMAL.toString())) {
            try {
                return new BigDecimal(cellString);
            } catch (NumberFormatException var8) {
                log.warn("数据列：{},数据转换异常{}", typeName, var8.getMessage());
                return new BigDecimal("0");
            }
        } else {
            throw new BaseKnownException(5000, "列转化失败");
        }
    }
}