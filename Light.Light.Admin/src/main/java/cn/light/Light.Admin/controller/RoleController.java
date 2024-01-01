package cn.light.Light.Admin.controller;
import cn.light.common.annotation.Log;
import cn.light.common.dto.role.RoleDTO;
import cn.light.common.dto.role.RoleListDTO;
import cn.light.common.dto.role.RoleQueryDTO;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.utils.DtoMapper;
import cn.light.common.utils.PageUtil;
import cn.light.entity.entity.KdRole;
import cn.light.entity.mapper.RoleMapper;
import cn.light.entity.repository.RoleRepository;

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
 * <p>Description: 所属客户管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 18:55:05</p >
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

    @Operation(summary = "分页查询所属客户")
    @PutMapping("/listPage")
    public Page<RoleListDTO> listPage(@RequestBody @Valid RoleQueryDTO queryDTO){
        Page<KdRole> dataPages  =  PageUtil.getPage(roleMapper::listPage, queryDTO);
        return DtoMapper.convertPage(dataPages, RoleListDTO.class);
    }

    @Operation(summary = "新增活更新所属客户")
    @PostMapping("/save")
    @Log("新增|修改所属客户")
    public void save(@RequestBody @Valid RoleDTO roleDTO){
        KdRole kdRole = DtoMapper.convert(roleDTO, KdRole.class);
        roleRepository.save(kdRole);
    }

    @Operation(summary = "查询全部所属客户")
    @GetMapping("/list")
    public List<RoleListDTO> list(){
        List<KdRole> all = roleRepository.findAll();
        return DtoMapper.convertList(all, RoleListDTO.class);
    }

    @Operation(summary = "查询")
    @GetMapping("/find/{id}")
    public RoleDTO find(@PathVariable Integer id){
        KdRole one = roleRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, RoleDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除所属客户")
    public void delete(@PathVariable Integer id) {
        roleRepository.deleteById(id);
    }

}