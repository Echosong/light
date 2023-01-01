package com.kdao.light.common.component;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: </p >
 * <p>Description: 用作生产自增code，id等</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-10-3018:27</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
public class SerialUtil {
    private static final Logger log = LoggerFactory.getLogger(SerialUtil.class);
    private final StringRedisTemplate stringRedisTemplate;

    public SerialUtil(StringRedisTemplate template) {
        this.stringRedisTemplate = template;
    }

    public String getSerialNumber(String service) {
        return this.getSerialNumber(service, 4);
    }

    public String getSerialNumber(String service, int numLength) {
        return this.getSerialNumber(service, null, numLength);
    }

    public String getSerialNumber(String service, String siteNum, int numLength) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateString = simpleDateFormat.format(new Date());
        String key;
        if (StrUtil.isEmpty(siteNum)) {
            key = String.format("SerialNumber-%s-%s", service, dateString);
        } else {
            key = String.format("SerialNumber-%s-%s-%s", siteNum, service, dateString);
        }

        String values = this.stringRedisTemplate.opsForValue().get(key);
        if (StrUtil.isEmpty(values)) {
            values = "0";
        }

        int num;
        try {
            num = Integer.parseInt(values);
        } catch (NumberFormatException var10) {
            log.error("序列化数据异常请检查redis中，service名称为" + service + "的序号是否正确");
            throw var10;
        }

        String number = String.format("%0" + numLength + "d", num + 1);
        this.depositRedis(key, number);
        return dateString + number;
    }

    private void depositRedis(String key, String value) {
        this.stringRedisTemplate.opsForValue().set(key, value);
        this.stringRedisTemplate.expire(key, 1L, TimeUnit.DAYS);
    }
}