package com.kdao.light.controller;

import cn.hutool.core.util.StrUtil;
import com.kdao.light.common.dto.role.RoleDTO;
import com.kdao.light.common.dto.user.UserDTO;
import com.kdao.light.common.enums.BaseEnum;
import com.kdao.light.common.utils.DtoMapper;
import com.kdao.light.entity.KdUser;
import com.kdao.light.repository.RoleRepository;
import com.kdao.light.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * 后台首页接口处理
 * @author Administrator
 */
@Tag(name = "接口处理")
@RestController
@Slf4j
@Validated
public class HomeController extends BaseController {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public HomeController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }



    /**
     * 获取所有项目信息，主要市拿相关编号配置给前端使用
     * @return 项目列表
     */
    @GetMapping("/project")
    @Operation(summary = "获取所有项目信息")
    public List<RoleDTO> getList(){
        return DtoMapper.convertList(roleRepository.findAll(), RoleDTO.class);
    }

    /**
     * 注册用户信息
     * @param userDTO 注册新用户
     */
    @PostMapping("/reg")
    @Operation(summary = "注册用户")
    public void reg(@RequestBody UserDTO userDTO){
        KdUser userDb = DtoMapper.convert(userDTO, KdUser.class);
        //新注册用户暂时给0
        userDb.setState(0);
        userRepository.save(userDb);
    }

    /**
     * 约定通用方法（表得某个字段需要枚举时候，
     * 那么用 (表名+字段+Enum) 比如
     *  UserSateEnum 就表示 user 模型下 state 字段 对应得枚举）
     */
    @GetMapping("/getEnums")
    @Operation(summary = "(实体+字段+Enum) UserSateEnum ")
    public List<Map<String, Object>> getEnums(@Valid @NotBlank String enumName) throws ClassNotFoundException {
        return BaseEnum.toMap(StrUtil.upperFirst(enumName));
    }

}
