package cn.light.admin.controller;

import cn.light.common.annotation.ApiResultIgnore;
import cn.light.common.annotation.Log;
import cn.light.common.annotation.Permission;
import cn.light.common.consts.Consts;
import cn.light.packet.dto.tableColumn.TableColumnDTO;
import cn.light.packet.dto.tableColumn.TableColumnListDTO;
import cn.light.packet.dto.tableColumn.TableColumnQueryDTO;
import cn.light.server.service.TableColumnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *  存储列设置表 控制器
 *  email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @date : 2024-07-28 22:43:24
 */
@Tag(name = "存储列设置表 控制器")
@RestController
@RequestMapping("/tableColumn")
public class TableColumnController extends BaseController{

    @Resource
    private TableColumnService tableColumnService;

    @Operation(summary = "分页查询存储列设置表")
    @PutMapping("/listPage")
    public Page<TableColumnListDTO> listPage(@RequestBody @Valid TableColumnQueryDTO queryDTO){
        return tableColumnService.listPage(queryDTO);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出存储列设置表")
    public ResponseEntity<byte[]> export(@RequestBody @Valid TableColumnQueryDTO queryDTO) {
        return tableColumnService.export(queryDTO);
    }

    @Operation(summary = "新增活更新存储列设置表")
    @PostMapping("/save")
    @Log("新增|修改存储列设置表")
    public void save(@RequestBody @Valid TableColumnDTO tableColumnDTO){
         tableColumnService.save(tableColumnDTO);
    }


    @Operation(summary = "查询单个明细")
    @GetMapping("/find/{id}")
    public TableColumnDTO find(@PathVariable Integer id){
        return tableColumnService.find(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除存储列设置表")
    @Permission(roles = Consts.ROLE_ADMIN_CODE)
    public void delete(@PathVariable(value="id") Integer id) {
        tableColumnService.delete(id);
    }

    

}