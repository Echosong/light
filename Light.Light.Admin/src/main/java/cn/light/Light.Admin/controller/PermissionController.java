package cn.light.Light.Admin.controller;
import cn.light.common.annotation.Log;
import cn.light.common.dto.permission.PermissionDTO;
import cn.light.common.dto.permission.PermissionListDTO;
import cn.light.common.dto.permission.PermissionQueryDTO;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.utils.DtoMapper;
import cn.light.common.utils.PageUtil;
import cn.light.entity.entity.KdPermission;
import cn.light.entity.mapper.PermissionMapper;
import cn.light.entity.repository.PermissionRepository;

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
 * <p>Description: 权限管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 18:55:05</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "权限 控制器")
@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseController{
    @Resource
    private  PermissionRepository permissionRepository;

    @Resource
    private PermissionMapper permissionMapper;

    @Operation(summary = "分页查询权限")
    @PutMapping("/listPage")
    public Page<PermissionListDTO> listPage(@RequestBody @Valid PermissionQueryDTO queryDTO){
        Page<KdPermission> dataPages  =  PageUtil.getPage(permissionMapper::listPage, queryDTO);
        return DtoMapper.convertPage(dataPages, PermissionListDTO.class);
    }

    @Operation(summary = "新增活更新权限")
    @PostMapping("/save")
    @Log("新增|修改权限")
    public void save(@RequestBody @Valid PermissionDTO permissionDTO){
        KdPermission kdPermission = DtoMapper.convert(permissionDTO, KdPermission.class);
        permissionRepository.save(kdPermission);
    }

    @Operation(summary = "查询全部权限")
    @GetMapping("/list")
    public List<PermissionListDTO> list(){
        List<KdPermission> all = permissionRepository.findAll();
        return DtoMapper.convertList(all, PermissionListDTO.class);
    }

    @Operation(summary = "查询")
    @GetMapping("/find/{id}")
    public PermissionDTO find(@PathVariable Integer id){
        KdPermission one = permissionRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, PermissionDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除权限")
    public void delete(@PathVariable Integer id) {
        permissionRepository.deleteById(id);
    }

}