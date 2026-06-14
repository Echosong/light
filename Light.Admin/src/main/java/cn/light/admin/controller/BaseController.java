package cn.light.admin.controller;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 基类控制器 - 获取真实客户端 IP（安全版）
 * - 正确处理多级代理场景下的 X-Forwarded-For / X-Real-IP / Proxy-Client-IP
 * - 取第一个非 unknown、非内网的真实客户端 IP
 */
public class BaseController {

    private static final String[] IP_HEADERS = new String[]{
            "X-Forwarded-For",
            "X-Real-IP",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR"
    };

    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST_V4 = "127.0.0.1";
    private static final String LOCALHOST_V6 = "0:0:0:0:0:0:0:1";

    @Autowired
    protected HttpServletRequest request;

    /**
     * 获取请求主机 IP 地址，如通过多层代理则取第一个非内网 IP
     */
    public static String getRemoteIp(HttpServletRequest request) {
        if (request == null) {
            return UNKNOWN;
        }
        String ip = null;
        for (String header : IP_HEADERS) {
            String value = request.getHeader(header);
            if (StrUtil.isBlank(value) || UNKNOWN.equalsIgnoreCase(value)) {
                continue;
            }
            // X-Forwarded-For 可能是 "client, proxy1, proxy2"，以逗号分隔
            int commaIdx = value.indexOf(',');
            if (commaIdx > 0) {
                ip = value.substring(0, commaIdx).trim();
            } else {
                ip = value.trim();
            }
            if (!isInternalIp(ip)) {
                break;
            }
        }

        if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (LOCALHOST_V4.equals(ip) || LOCALHOST_V6.equals(ip)) {
                try {
                    InetAddress local = InetAddress.getLocalHost();
                    ip = local.getHostAddress();
                } catch (UnknownHostException ignored) {
                    // 保留原值即可
                }
            }
        }

        // 最终再做一次清洗：多个 IP 取第一个
        if (ip != null && ip.contains(",")) {
            ip = ip.substring(0, ip.indexOf(',')).trim();
        }
        // 防范非法字符（IPv6 含冒号是合法的）
        if (ip != null && ip.length() > 45) {
            ip = UNKNOWN;
        }
        return ip;
    }

    /** 简单判断是否为内网回环 IP */
    private static boolean isInternalIp(String ip) {
        if (ip == null) {
            return true;
        }
        return ip.startsWith("10.")
                || ip.startsWith("192.168.")
                || ip.startsWith("172.16.")
                || ip.startsWith("172.17.")
                || ip.startsWith("172.18.")
                || ip.startsWith("172.19.")
                || ip.startsWith("172.2")
                || ip.startsWith("172.30.")
                || ip.startsWith("172.31.")
                || LOCALHOST_V4.equals(ip)
                || LOCALHOST_V6.equals(ip);
    }

    protected String getRemoteIp() {
        return getRemoteIp(request);
    }
}
