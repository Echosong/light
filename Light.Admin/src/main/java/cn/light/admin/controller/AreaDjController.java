package cn.light.admin.controller;
import cn.light.common.annotation.*;
import cn.light.common.consts.Consts;
import cn.light.packet.dto.areaDj.AreaDjDTO;
import cn.light.packet.dto.areaDj.AreaDjListDTO;
import cn.light.packet.dto.areaDj.AreaDjQueryDTO;
import cn.light.server.service.AreaDjService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.*;

/**
 *  行政区划实体 控制器
 *  email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @date : 2024-10-05 21:10:39
 */
@Tag(name = "行政区划实体 控制器")
@RestController
@RequestMapping("/areaDj")
public class AreaDjController extends BaseController{

    @Resource
    private AreaDjService areaDjService;

    @Operation(summary = "分页查询行政区划实体")
    @PutMapping("/listPage")
    public Page<AreaDjListDTO> listPage(@RequestBody @Valid AreaDjQueryDTO queryDTO){
        return areaDjService.listPage(queryDTO);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出行政区划实体")
    public ResponseEntity<byte[]> export(@RequestBody @Valid AreaDjQueryDTO queryDTO) {
        return areaDjService.export(queryDTO);
    }

    @Operation(summary = "新增活更新行政区划实体")
    @PostMapping("/save")
    @Log("新增|修改行政区划实体")
    public void save(@RequestBody @Valid AreaDjDTO areaDjDTO){
         areaDjService.save(areaDjDTO);
    }


    @Operation(summary = "查询单个明细")
    @GetMapping("/find/{id}")
    public AreaDjDTO find(@PathVariable Integer id){
        return areaDjService.find(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除行政区划实体")
    @Permission(roles = Consts.ROLE_ADMIN_CODE)
    public void delete(@PathVariable(value="id") Integer id) {
        areaDjService.delete(id);
    }

    
    @Operation(summary = "简单查询行政区划实体")
    @GetMapping("/getMap")
    public List<Map<String, Object>> getMap(){
         return areaDjService.getMap();
    }
    

}