package com.kdao.light.api;

import com.kdao.light.common.annotation.NoPermission;
import com.kdao.light.common.dto.user.UserDTO;
import com.kdao.light.common.utils.DtoMapper;
import com.kdao.light.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 *  前端首页相关接口
 * @author : 二胡子
 * @version :1.0.0
 */
@RestController("api-home")
@RequestMapping("/home")
public class HomeController {

    private final UserRepository userRepository;

    @Autowired
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 前端接口
     * @return 欢迎语句
     */
    @GetMapping("/index")
    public String index(){
        return "可以告诉你些东dd东！！！！！！！！！！！！！！！";
    }


    @GetMapping("/users")
    @NoPermission
    public List<UserDTO> getUsers(){
       return DtoMapper.convertList(userRepository.findAll(), UserDTO.class);
    }
}