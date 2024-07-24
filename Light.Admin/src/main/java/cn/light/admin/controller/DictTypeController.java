package cn.light.admin.controller;
import cn.light.common.annotation.*;
import cn.light.common.consts.Consts;
import cn.light.packet.dto.dictType.DictTypeDTO;
import cn.light.packet.dto.dictType.DictTypeListDTO;
import cn.light.packet.dto.dictType.DictTypeQueryDTO;
import cn.light.server.service.DictTypeService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.*;

/**
 *  字典类型 控制器
 *  email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @date : 2024-07-24 22:17:47
 */
@Tag(name = "字典类型 控制器")
@RestController
@RequestMapping("/dictType")
public class DictTypeController extends BaseController{

    @Resource
    private DictTypeService dictTypeService;

    @Operation(summary = "分页查询字典类型")
    @PutMapping("/listPage")
    public Page<DictTypeListDTO> listPage(@RequestBody @Valid DictTypeQueryDTO queryDTO){
        return dictTypeService.listPage(queryDTO);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出字典类型")
    public ResponseEntity<byte[]> export(@RequestBody @Valid DictTypeQueryDTO queryDTO) {
        return dictTypeService.export(queryDTO);
    }

    @Operation(summary = "新增活更新字典类型")
    @PostMapping("/save")
    @Log("新增|修改字典类型")
    public void save(@RequestBody @Valid DictTypeDTO dictTypeDTO){
         dictTypeService.save(dictTypeDTO);
    }


    @Operation(summary = "查询单个明细")
    @GetMapping("/find/{id}")
    public DictTypeDTO find(@PathVariable Integer id){
        return dictTypeService.find(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除字典类型")
    @Permission(roles = Consts.ROLE_ADMIN_CODE)
    public void delete(@PathVariable(value="id") Integer id) {
        dictTypeService.delete(id);
    }

    

}