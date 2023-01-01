package com.kdao.light.common.exception;

import com.kdao.light.common.utils.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-10-3019:31</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Controller
@Api(tags = "处理404问题")
public class NotFundException  implements ErrorController  {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @ResponseBody
    @GetMapping("/error")
    public ResultVO error404(){
        return  ResultVO.error(404, "找不到该请求接口");
    }
}

