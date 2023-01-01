package com.kdao.light.controller;

import com.kdao.light.common.dto.config.ConfigDTO;
import com.kdao.light.common.utils.DtoMapper;
import com.kdao.light.entity.KdConfig;
import com.kdao.light.service.ConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Title: </p >
 * <p>Description: 键值配置</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/28 002817:14</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@RestController
@Api(tags = "系统配置")
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
    @ApiOperation("查询全部配置数据")
    @GetMapping("/list")
    public List<ConfigDTO> list(){
        return configService.listAll();
    }

    /**
     * 修改或者新建配置
     * @param kdConfig
     */
    @PostMapping("/save")
    @ApiOperation("修改或者新建配置")
    public void save(@RequestBody ConfigDTO kdConfig){
        configService.save(DtoMapper.convert(kdConfig, KdConfig.class));
    }

    /**
     * 批量修改
     * @param configDTOS
     */
    @PostMapping("saveList")
    @ApiOperation("/批量修改")
    public void saveList(@RequestBody List<ConfigDTO> configDTOS){
        configDTOS.stream().forEach(t->{
            configService.save(DtoMapper.convert(t, KdConfig.class));
        });
    }
}
