package com.light.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: light</p >
 * <p>Description: IndexController</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2024/1/1 15:22 </p >
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping("/index")
    public String index(){
        return "这里是前台Api";
    }
}