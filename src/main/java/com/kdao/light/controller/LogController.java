package com.kdao.light.controller;
import com.kdao.light.common.annotation.Log;
import com.kdao.light.common.dto.log.LogDTO;
import com.kdao.light.common.dto.log.LogListDTO;
import com.kdao.light.common.dto.log.LogQueryDTO;
import com.kdao.light.common.exception.BaseKnownException;
import com.kdao.light.common.utils.DtoMapper;
import com.kdao.light.common.utils.PageUtil;
import com.kdao.light.entity.KdLog;
import com.kdao.light.mapper.LogMapper;
import com.kdao.light.repository.LogRepository;

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
 * <p>Description: 日志管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2023-12-28 21:30:19</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "日志 控制器")
@RestController
@RequestMapping("/log")
public class LogController extends BaseController{
    @Resource
    private  LogRepository logRepository;

    @Resource
    private LogMapper logMapper;

    @Operation(summary = "分页查询日志")
    @PutMapping("/listPage")
    public Page<LogListDTO> listPage(@RequestBody @Valid LogQueryDTO queryDTO){
        Page<KdLog> dataPages  =  PageUtil.getPage(logMapper::listPage, queryDTO);
        return DtoMapper.convertPage(dataPages, LogListDTO.class);
    }

    @Operation(summary = "新增活更新日志")
    @PostMapping("/save")
    @Log("新增|修改日志")
    public void save(@RequestBody @Valid LogDTO logDTO){
        KdLog kdLog = DtoMapper.convert(logDTO, KdLog.class);
        logRepository.save(kdLog);
    }

    @Operation(summary = "查询全部日志")
    @GetMapping("/list")
    public List<LogListDTO> list(){
        List<KdLog> all = logRepository.findAll();
        return DtoMapper.convertList(all, LogListDTO.class);
    }

    @Operation(summary = "查询")
    @GetMapping("/find/{id}")
    public LogDTO find(@PathVariable Integer id){
        KdLog one = logRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, LogDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除日志")
    public void delete(@PathVariable Integer id) {
        logRepository.deleteById(id);
    }

}