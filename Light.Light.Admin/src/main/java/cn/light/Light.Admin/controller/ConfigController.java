package cn.light.Light.Admin.controller;
import cn.light.common.annotation.Log;
import cn.light.common.dto.config.ConfigDTO;
import cn.light.common.dto.config.ConfigListDTO;
import cn.light.common.dto.config.ConfigQueryDTO;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.utils.DtoMapper;
import cn.light.common.utils.PageUtil;
import cn.light.entity.entity.KdConfig;
import cn.light.entity.mapper.ConfigMapper;
import cn.light.entity.repository.ConfigRepository;

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
 * <p>Description: 系统配置管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 18:55:05</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "系统配置 控制器")
@RestController
@RequestMapping("/config")
public class ConfigController extends BaseController{
    @Resource
    private  ConfigRepository configRepository;

    @Resource
    private ConfigMapper configMapper;

    @Operation(summary = "分页查询系统配置")
    @PutMapping("/listPage")
    public Page<ConfigListDTO> listPage(@RequestBody @Valid ConfigQueryDTO queryDTO){
        Page<KdConfig> dataPages  =  PageUtil.getPage(configMapper::listPage, queryDTO);
        return DtoMapper.convertPage(dataPages, ConfigListDTO.class);
    }

    @Operation(summary = "新增活更新系统配置")
    @PostMapping("/save")
    @Log("新增|修改系统配置")
    public void save(@RequestBody @Valid ConfigDTO configDTO){
        KdConfig kdConfig = DtoMapper.convert(configDTO, KdConfig.class);
        configRepository.save(kdConfig);
    }

    @Operation(summary = "查询全部系统配置")
    @GetMapping("/list")
    public List<ConfigListDTO> list(){
        List<KdConfig> all = configRepository.findAll();
        return DtoMapper.convertList(all, ConfigListDTO.class);
    }

    @Operation(summary = "查询")
    @GetMapping("/find/{id}")
    public ConfigDTO find(@PathVariable Integer id){
        KdConfig one = configRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, ConfigDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除系统配置")
    public void delete(@PathVariable Integer id) {
        configRepository.deleteById(id);
    }

}