package com.kdao.light.common.utils;

import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/29 002910:41</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
public class IpUtil {

    public static   String getXForwardedForIp(String ip) {
        //ip 无效直接返回
        if (!ipValid(ip)) {
            return "";
        }
        if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (String strIp : ips) {
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
            return ip;
        }

        return ip;
    }

    public  static   Boolean ipValid(String ip) {
        if (StrUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            return false;
        }
        return true;
    }

    public  static   String realIp(String ip) {
        if (StrUtil.isEmpty(ip)) {
            return "";
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    public static String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }
}
