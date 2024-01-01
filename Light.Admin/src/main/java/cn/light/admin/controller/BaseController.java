package cn.light.admin.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 基类
 * @author : echosong
 * @version :1.0.0
 */
public class BaseController {
    @Autowired
    protected HttpServletRequest request;

    public static String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }
    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
     *
     * @return 返回ip信息
     */
    protected String getRemoteIp() {
        return getRemortIP(request);
    }


}
