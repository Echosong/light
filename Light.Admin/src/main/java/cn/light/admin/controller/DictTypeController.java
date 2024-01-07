package cn.light.admin.controller;
import cn.light.common.annotation.*;
import cn.light.packet.dto.dictType.DictTypeDTO;
import cn.light.packet.dto.dictType.DictTypeListDTO;
import cn.light.packet.dto.dictType.DictTypeQueryDTO;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.*;
import cn.light.entity.entity.SysDictType;
import cn.light.entity.mapper.DictTypeMapper;
import cn.light.entity.repository.DictTypeRepository;
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
 * <p>Description: 字典类型管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-07 23:00:48</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "字典类型 控制器")
@RestController
@RequestMapping("/dictType")
public class DictTypeController extends BaseController{
    @Resource
    private  DictTypeRepository dictTypeRepository;

    @Resource
    private DictTypeMapper dictTypeMapper;

    @Operation(summary = "分页查询字典类型")
    @PutMapping("/listPage")
    public Page<DictTypeListDTO> listPage(@RequestBody @Valid DictTypeQueryDTO queryDTO){
        Page<SysDictType> dataPages  =  PageUtil.getPage(dictTypeMapper::listPage, queryDTO);
        return DtoMapper.convertPage(dataPages, DictTypeListDTO.class);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出字典类型")
    public ResponseEntity<byte[]> export(@RequestBody @Valid DictTypeQueryDTO queryDTO) throws IOException, IllegalAccessException {
        List<SysDictType> all = dictTypeMapper.listPage(queryDTO);
        String fileName = "DictType"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        return ExcelUtil.generateImportFile(DtoMapper.convertList(all, DictTypeListDTO.class), fileName, DictTypeListDTO.class);
    }

    @Operation(summary = "新增活更新字典类型")
    @PostMapping("/save")
    @Log("新增|修改字典类型")
    public void save(@RequestBody @Valid DictTypeDTO dictTypeDTO){
        SysDictType sysDictType = DtoMapper.convert(dictTypeDTO, SysDictType.class);
        dictTypeRepository.save(sysDictType);
    }

    @Operation(summary = "查询全部字典类型")
    @GetMapping("/list")
    public List<DictTypeListDTO> list(){
        List<SysDictType> all = dictTypeRepository.findAll();
        return DtoMapper.convertList(all, DictTypeListDTO.class);
    }

    @Operation(summary = "查询")
    @GetMapping("/find/{id}")
    public DictTypeDTO find(@PathVariable Integer id){
        SysDictType one = dictTypeRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, DictTypeDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除字典类型")
    public void delete(@PathVariable Integer id) {
        dictTypeRepository.deleteById(id);
    }

}