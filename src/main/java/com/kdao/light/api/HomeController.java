package com.kdao.light.api;

import com.kdao.light.common.annotation.NoPermission;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *  前端首页相关接口
 * @author : 二胡子
 * @version :1.0.0
 */
@RestController("api-home")
@RequestMapping("/home")
public class HomeController {

    /**
     * 前端接口
     * @return 欢迎语句
     */
    @GetMapping("/index")
    @NoPermission
    public String index(){
        return "这里是前端接口";
    }
}