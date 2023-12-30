package com.kdao.light.controller;


import com.kdao.light.common.annotation.Log;
import com.kdao.light.entity.KdRole;
import com.kdao.light.entity.KdUserRole;
import com.kdao.light.repository.RoleRepository;
import com.kdao.light.repository.UserRoleRepository;
import com.kdao.light.service.RoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *部门，角色
 * @author : echosong
 * @version :1.0.0
 */
@RestController
@Tag(name = "部门，角色")
@RequestMapping("/role")
public class RoleController extends BaseController{
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final UserRoleRepository userRoleRepositroy;

    @Autowired
    public RoleController(RoleRepository roleRepository, RoleService roleService, UserRoleRepository userRoleRepositroy) {
        this.userRoleRepositroy = userRoleRepositroy;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
    }

    /**
     * 添加修改角色信息
     * @param role
     */
    @Operation(summary = "添加修改角色信息")
    @PostMapping("/save")
    @Log("修改角色")
    public void save(@RequestBody KdRole role){
        roleRepository.save(role);
    }

    /**
     * 一次性获取所有角色，角色不会有很多，所以不需要分页
     * @return
     */
    @PutMapping("/list")
    @Operation(summary = "一次性获取所有角色，角色不会有很多，所以不需要分页")
    public List<KdRole> list(){
        return roleRepository.findAll();
    }

    /**
     * 根据用户获取角色
     * @param userId
     * @return
     */
    @GetMapping("/listByUser/{userId}")
    @Operation(summary = "根据用户获取角色")
    public List<KdRole> listByUser(@PathVariable Integer userId){
        return roleService.listByUser(userId);
    }

    /**
     * 删除某个角色
     * @param id
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除某个角色")
    @Log("删除某个角色")
    public void delete(@PathVariable Integer id){
        Optional<KdRole> byId = roleRepository.findById(id);
        Assert.isTrue(byId.isPresent(), "部门不存在");
        List<KdUserRole> allByRoleId = userRoleRepositroy.findAllByRoleId(id);
        Assert.isTrue(allByRoleId.isEmpty(), "已经有用户归属改部门，所以不能删除");
        roleRepository.delete(byId.get());
    }

}
