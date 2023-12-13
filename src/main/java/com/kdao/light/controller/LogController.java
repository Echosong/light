package com.kdao.light.controller;

import com.kdao.light.common.dto.log.LogDTO;
import com.kdao.light.common.dto.log.LogListDTO;
import com.kdao.light.common.dto.log.LogQueryDTO;
import com.kdao.light.common.exception.BaseKnownException;
import com.kdao.light.common.utils.DtoMapper;
import com.kdao.light.entity.KdLog;
import com.kdao.light.entity.KdUser;
import com.kdao.light.repository.LogRepository;
import com.kdao.light.service.LogService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 日志 控制器
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "日志 控制器")
@RestController
@RequestMapping("/log")
public class LogController extends BaseController{

    private final LogRepository logRepository;
    private final LogService logService;

    @Autowired
    public LogController(LogRepository logRepository, LogService logService) {
        this.logRepository = logRepository;
        this.logService = logService;
    }

    /**
     * 分页获取 日志
     * @param queryDTO 查询条件
     */
    @Operation(summary = "分页查询日志")
    @PutMapping("/listPage")
    public Page<LogListDTO> listPage(@RequestBody @Valid LogQueryDTO queryDTO){
        Page<KdLog> dataPages = logRepository.listPage(queryDTO.getDescription(),queryDTO.getStartCreateTime(),queryDTO.getEndCreateTime(),  queryDTO.getRequest());
        return DtoMapper.convertPage(dataPages, LogListDTO.class);
    }

    @GetMapping("/set")
    public void setUserList(){
        logService.setUserList();
    }

    @GetMapping("/get")
    public List<KdUser> getUserList(){
        return  logService.getUserList();
    }


    /**
     * 获取单个信息 日志
     * @param id 日志id
     */
    @Operation(summary = "查询")
    @GetMapping("/find/{id}")
    public LogDTO find(@PathVariable Integer id){
        KdLog one = logRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, LogDTO.class);
    }


}