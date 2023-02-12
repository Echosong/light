package com.kdao.light.api;

import com.kdao.light.common.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002719:52</p >
 *
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
