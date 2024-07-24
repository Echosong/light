package cn.light.admin.controller;
import cn.light.common.annotation.*;
import cn.light.common.consts.Consts;
import cn.light.packet.dto.config.ConfigDTO;
import cn.light.packet.dto.config.ConfigListDTO;
import cn.light.packet.dto.config.ConfigQueryDTO;
import cn.light.server.service.ConfigService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.*;

/**
 *  系统配置 控制器
 *  email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @date : 2024-07-24 22:40:25
 */
@Tag(name = "系统配置 控制器")
@RestController
@RequestMapping("/config")
public class ConfigController extends BaseController{

    @Resource
    private ConfigService configService;

    @Operation(summary = "分页查询系统配置")
    @PutMapping("/listPage")
    public Page<ConfigListDTO> listPage(@RequestBody @Valid ConfigQueryDTO queryDTO){
        return configService.listPage(queryDTO);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出系统配置")
    public ResponseEntity<byte[]> export(@RequestBody @Valid ConfigQueryDTO queryDTO) {
        return configService.export(queryDTO);
    }

    @Operation(summary = "新增活更新系统配置")
    @PostMapping("/save")
    @Log("新增|修改系统配置")
    public void save(@RequestBody @Valid ConfigDTO configDTO){
         configService.save(configDTO);
    }


    @Operation(summary = "查询单个明细")
    @GetMapping("/find/{id}")
    public ConfigDTO find(@PathVariable Integer id){
        return configService.find(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除系统配置")
    @Permission(roles = Consts.ROLE_ADMIN_CODE)
    public void delete(@PathVariable(value="id") Integer id) {
        configService.delete(id);
    }

    

}