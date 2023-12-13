package com.kdao.light.controller;

import com.kdao.light.common.utils.IpUtil;
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


    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
     *
     * @return 返回ip信息
     */
    protected String getRemoteIp() {
        return IpUtil.getRemortIP(request);
    }


}
