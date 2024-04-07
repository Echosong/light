package cn.light.admin.controller;
import cn.light.common.annotation.Log;
import cn.light.entity.entity.SysResourceCategory;
import cn.light.entity.entity.SysRole;
import cn.light.entity.entity.SysUserRole;
import cn.light.entity.mapper.RoleMapper;
import cn.light.entity.repository.RoleRepository;

import cn.light.entity.repository.UserRoleRepository;
import cn.light.server.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
    private RoleMapper roleMapper;
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
    public void save(@RequestBody SysRole role){
        roleRepository.save(role);
    }

    /**
     * 一次性获取所有角色，角色不会有很多，所以不需要分页
     * @return
     */
    @PutMapping("/list")
    @Operation(summary = "一次性获取所有角色，角色不会有很多，所以不需要分页")
    public List<SysRole> list(){
        return roleRepository.findAll();
    }

    /**
     * 根据用户获取角色
     * @param userId
     * @return
     */
    @GetMapping("/listByUser/{userId}")
    @Operation(summary = "根据用户获取角色")
    public List<SysRole> listByUser(@PathVariable Integer userId){
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
        Optional<SysRole> byId = roleRepository.findById(id);
        Assert.isTrue(byId.isPresent(), "部门不存在");
        List<SysUserRole> allByRoleId = userRoleRepositroy.findAllByRoleId(id);
        Assert.isTrue(allByRoleId.isEmpty(), "已经有用户归属改部门，所以不能删除");
        roleRepository.delete(byId.get());
    }

    @Operation(summary = "简单查询资源分类")
    @GetMapping("/getMap")
    public List<Map<String, Object>> getMap(){
        List<SysRole> all = roleMapper.selectList(new LambdaQueryWrapper<SysRole>()
                .select(SysRole::getId, SysRole::getName)
                .orderByDesc(SysRole::getId)
        );
        List<Map<String, Object>> maps = new ArrayList<>();
        for (SysRole item : all) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("name", item.getName());
            maps.add(map);
        }
        return maps;
    }


}