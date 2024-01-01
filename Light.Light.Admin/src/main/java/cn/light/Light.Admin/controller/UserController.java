package cn.light.Light.Admin.controller;
import cn.light.common.annotation.Log;
import cn.light.common.dto.user.UserDTO;
import cn.light.common.dto.user.UserListDTO;
import cn.light.common.dto.user.UserQueryDTO;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.utils.DtoMapper;
import cn.light.common.utils.PageUtil;
import cn.light.entity.entity.KdUser;
import cn.light.entity.mapper.UserMapper;
import cn.light.entity.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>Title: </p >
 * <p>Description: 用户管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 18:55:05</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "用户 控制器")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    @Resource
    private  UserRepository userRepository;

    @Resource
    private UserMapper userMapper;

    @Operation(summary = "分页查询用户")
    @PutMapping("/listPage")
    public Page<UserListDTO> listPage(@RequestBody @Valid UserQueryDTO queryDTO){
        Page<KdUser> dataPages  =  PageUtil.getPage(userMapper::listPage, queryDTO);
        return DtoMapper.convertPage(dataPages, UserListDTO.class);
    }

    @Operation(summary = "新增活更新用户")
    @PostMapping("/save")
    @Log("新增|修改用户")
    public void save(@RequestBody @Valid UserDTO userDTO){
        KdUser kdUser = DtoMapper.convert(userDTO, KdUser.class);
        userRepository.save(kdUser);
    }

    @Operation(summary = "查询全部用户")
    @GetMapping("/list")
    public List<UserListDTO> list(){
        List<KdUser> all = userRepository.findAll();
        return DtoMapper.convertList(all, UserListDTO.class);
    }

    @Operation(summary = "查询")
    @GetMapping("/find/{id}")
    public UserDTO find(@PathVariable Integer id){
        KdUser one = userRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, UserDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除用户")
    public void delete(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

}