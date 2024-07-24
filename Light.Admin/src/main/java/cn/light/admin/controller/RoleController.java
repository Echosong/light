package cn.light.admin.controller;

import cn.light.common.annotation.Log;
import cn.light.common.annotation.Permission;
import cn.light.common.conts.Consts;
import cn.light.entity.entity.SysRole;
import cn.light.packet.dto.role.RoleDTO;
import cn.light.packet.dto.role.RoleListDTO;
import cn.light.server.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 所属客户管理
 * listPage,save,delete,find,list
 *
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "所属客户 控制器")
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController{
    @Resource
    private  RoleService roleService;


    /**
     * 添加修改角色信息
     */
    @Operation(summary = "添加修改角色信息")
    @PostMapping("/save")
    @Log("修改角色")
    public void save(@RequestBody @Valid RoleDTO role){
        roleService.save(role);
    }

    /**
     * 一次性获取所有角色，角色不会有很多，所以不需要分页
     */
    @PutMapping("/list")
    @Operation(summary = "一次性获取所有角色，角色不会有很多，所以不需要分页")
    public List<RoleListDTO> list(){
        return roleService.selectAll();
    }

    /**
     * 根据用户获取角色
     */
    @GetMapping("/listByUser/{userId}")
    @Operation(summary = "根据用户获取角色")
    public List<SysRole> listByUser(@PathVariable Integer userId){
        return roleService.listByUser(userId);
    }

    /**
     * 删除某个角色
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除某个角色")
    @Log("删除某个角色")
    @Permission(roles = Consts.ROLE_ADMIN_CODE)
    public void delete(@PathVariable Integer id){
        roleService.delete(id);
    }

    /**
     * 简单查询资源分类
     */
    @Operation(summary = "简单查询资源分类")
    @GetMapping("/getMap")
    public List<Map<String, Object>> getMap(){
        return roleService.getMap();
    }


}