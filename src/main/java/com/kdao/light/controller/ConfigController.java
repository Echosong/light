package com.kdao.light.controller;

import com.kdao.light.common.dto.config.ConfigDTO;
import com.kdao.light.common.utils.DtoMapper;
import com.kdao.light.entity.KdConfig;
import com.kdao.light.service.ConfigService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 键值配置
 * @author : echosong
 * @version :1.0.0
 */
@RestController
@Tag(name = "系统配置")
@RequestMapping("/config")
public class ConfigController {
    private final ConfigService configService;

    @Autowired
    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    /**
     * 查询全部配置数据
     * @return
     */
    @Operation(summary = "查询全部配置数据")
    @GetMapping("/list")
    public List<ConfigDTO> list(){
        return configService.listAll();
    }

    /**
     * 修改或者新建配置
     * @param kdConfig
     */
    @PostMapping("/save")
    @Operation(summary = "修改或者新建配置")
    public void save(@RequestBody ConfigDTO kdConfig){
        configService.save(DtoMapper.convert(kdConfig, KdConfig.class));
    }

    /**
     * 批量修改
     * @param configDTOS
     */
    @PostMapping("saveList")
    @Operation(summary = "/批量修改")
    public void saveList(@RequestBody List<ConfigDTO> configDTOS){
        configDTOS.stream().forEach(t->{
            configService.save(DtoMapper.convert(t, KdConfig.class));
        });
    }
}
