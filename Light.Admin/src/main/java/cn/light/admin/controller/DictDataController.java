package cn.light.admin.controller;

import cn.light.common.annotation.ApiResultIgnore;
import cn.light.common.annotation.Log;
import cn.light.common.annotation.Permission;
import cn.light.common.consts.Consts;
import cn.light.packet.dto.dictData.DictDataDTO;
import cn.light.packet.dto.dictData.DictDataListDTO;
import cn.light.packet.dto.dictData.DictDataQueryDTO;
import cn.light.server.service.DictDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *  字典数据 控制器
 *  email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @date : 2024-07-24 22:17:47
 */
@Tag(name = "字典数据 控制器")
@RestController
@RequestMapping("/dictData")
public class DictDataController extends BaseController{

    @Resource
    private DictDataService dictDataService;

    @Operation(summary = "分页查询字典数据")
    @PutMapping("/listPage")
    public Page<DictDataListDTO> listPage(@RequestBody @Valid DictDataQueryDTO queryDTO){
        return dictDataService.listPage(queryDTO);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出字典数据")
    public ResponseEntity<byte[]> export(@RequestBody @Valid DictDataQueryDTO queryDTO) {
        return dictDataService.export(queryDTO);
    }

    @Operation(summary = "新增活更新字典数据")
    @PostMapping("/save")
    @Log("新增|修改字典数据")
    public void save(@RequestBody @Valid DictDataDTO dictDataDTO){
         dictDataService.save(dictDataDTO);
    }


    @Operation(summary = "查询单个明细")
    @GetMapping("/find/{id}")
    public DictDataDTO find(@PathVariable Integer id){
        return dictDataService.find(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除字典数据")
    @Permission(roles = Consts.ROLE_ADMIN_CODE)
    public void delete(@PathVariable(value="id") Integer id) {
        dictDataService.delete(id);
    }

    

}