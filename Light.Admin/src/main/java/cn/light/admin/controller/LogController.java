package cn.light.admin.controller;
import cn.light.common.annotation.*;
import cn.light.common.consts.Consts;
import cn.light.packet.dto.log.LogDTO;
import cn.light.packet.dto.log.LogListDTO;
import cn.light.packet.dto.log.LogQueryDTO;
import cn.light.server.service.LogService;
import cn.light.server.service.UserService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


/**
 *  日志 控制器
 *  email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @date : 2024-07-24 22:19:23
 */
@Tag(name = "日志 控制器")
@RestController
@RequestMapping("/log")
public class LogController extends BaseController{

    @Resource
    private LogService logService;
    @Resource
    private UserService userService;

    @Operation(summary = "分页查询日志")
    @PutMapping("/listPage")
    public Page<LogListDTO> listPage(@RequestBody @Valid LogQueryDTO queryDTO){
        return logService.listPage(queryDTO);
    }

    @Operation(summary = "获取当前登录用户的日志")
    @PutMapping("/currentListPage")
    public Page<LogListDTO> currentListPage(@RequestBody @Valid LogQueryDTO queryDTO){
        queryDTO.setUsername(userService.getUserCache().getUsername());
        return logService.listPage(queryDTO);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出日志")
    public ResponseEntity<byte[]> export(@RequestBody @Valid LogQueryDTO queryDTO) {
        return logService.export(queryDTO);
    }

    @Operation(summary = "新增活更新日志")
    @PostMapping("/save")
    @Log("新增|修改日志")
    public void save(@RequestBody @Valid LogDTO logDTO){
         logService.save(logDTO);
    }


    @Operation(summary = "查询单个明细")
    @GetMapping("/find/{id}")
    public LogDTO find(@PathVariable Integer id){
        return logService.find(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除日志")
    @Permission(roles = Consts.ROLE_ADMIN_CODE)
    public void delete(@PathVariable(value="id") Integer id) {
        logService.delete(id);
    }

    

}