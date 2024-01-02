package cn.light.admin.controller;
import cn.light.common.annotation.Log;
import cn.light.entity.entity.KdRole;
import cn.light.entity.entity.KdUserRole;
import cn.light.entity.repository.RoleRepository;

import cn.light.entity.repository.UserRoleRepository;
import cn.light.server.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>Title: </p >
 * <p>Description: 所属客户管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 20:15:44</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "所属客户 控制器")
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController{
    @Resource
    private  RoleRepository roleRepository;
    @Resource
    private  RoleService roleService;
    @Resource
    private  UserRoleRepository userRoleRepositroy;

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