package cn.light.admin.controller;
import cn.light.common.annotation.*;
import cn.light.packet.dto.dictData.DictDataDTO;
import cn.light.packet.dto.dictData.DictDataListDTO;
import cn.light.packet.dto.dictData.DictDataQueryDTO;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.*;
import cn.light.entity.entity.SysDictData;
import cn.light.entity.mapper.DictDataMapper;
import cn.light.entity.repository.DictDataRepository;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import cn.hutool.core.date.DateUtil;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.io.IOException;
import java.util.*;

/**
 * <p>Title: </p >
 * <p>Description: 字典数据管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-07 23:00:48</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "字典数据 控制器")
@RestController
@RequestMapping("/dictData")
public class DictDataController extends BaseController{
    @Resource
    private  DictDataRepository dictDataRepository;

    @Resource
    private DictDataMapper dictDataMapper;

    @Operation(summary = "分页查询字典数据")
    @PutMapping("/listPage")
    public Page<DictDataListDTO> listPage(@RequestBody @Valid DictDataQueryDTO queryDTO){
        Page<SysDictData> dataPages  =  PageUtil.getPage(dictDataMapper::listPage, queryDTO);
        return DtoMapper.convertPage(dataPages, DictDataListDTO.class);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出字典数据")
    public ResponseEntity<byte[]> export(@RequestBody @Valid DictDataQueryDTO queryDTO) throws IOException, IllegalAccessException {
        List<SysDictData> all = dictDataMapper.listPage(queryDTO);
        String fileName = "DictData"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        return ExcelUtil.generateImportFile(DtoMapper.convertList(all, DictDataListDTO.class), fileName, DictDataListDTO.class);
    }

    @Operation(summary = "新增活更新字典数据")
    @PostMapping("/save")
    @Log("新增|修改字典数据")
    public void save(@RequestBody @Valid DictDataDTO dictDataDTO){
        SysDictData sysDictData = DtoMapper.convert(dictDataDTO, SysDictData.class);
        dictDataRepository.save(sysDictData);
    }

    @Operation(summary = "查询全部字典数据")
    @GetMapping("/list")
    public List<DictDataListDTO> list(){
        List<SysDictData> all = dictDataRepository.findAll();
        return DtoMapper.convertList(all, DictDataListDTO.class);
    }

    @Operation(summary = "查询")
    @GetMapping("/find/{id}")
    public DictDataDTO find(@PathVariable Integer id){
        SysDictData one = dictDataRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, DictDataDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除字典数据")
    public void delete(@PathVariable Integer id) {
        dictDataRepository.deleteById(id);
    }

}