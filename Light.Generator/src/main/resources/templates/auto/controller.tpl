package #{PackageName}#;
import #{SYS_PATH}.common.annotation.*;
import #{SYS_PATH}.packet.dto.#{EntityName}#.#{UpEntityName}#DTO;
import #{SYS_PATH}.packet.dto.#{EntityName}#.#{UpEntityName}#ListDTO;
import #{SYS_PATH}.packet.dto.#{EntityName}#.#{UpEntityName}#QueryDTO;
import #{SYS_PATH}.common.exception.BaseKnownException;
import #{SYS_PATH}.common.util.*;
import #{SYS_PATH}.entity.entity.#{UpTableName}#;
import #{SYS_PATH}.entity.mapper.#{UpEntityName}#Mapper;
import #{SYS_PATH}.entity.repository.#{UpEntityName}#Repository;
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
 * <p>Description: #{tableInfo}#管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: #{localDate}#</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "#{tableInfo}# 控制器")
@RestController
@RequestMapping("/#{EntityName}#")
public class #{UpEntityName}#Controller extends BaseController{
    @Resource
    private  #{UpEntityName}#Repository #{EntityName}#Repository;

    @Resource
    private #{UpEntityName}#Mapper #{EntityName}#Mapper;

    @Operation(summary = "分页查询#{tableInfo}#")
    @PutMapping("/listPage")
    public Page<#{UpEntityName}#ListDTO> listPage(@RequestBody @Valid #{UpEntityName}#QueryDTO queryDTO){
        Page<#{UpTableName}#> dataPages  =  PageUtil.getPage(#{EntityName}#Mapper::listPage, queryDTO);
        return DtoMapper.convertPage(dataPages, #{UpEntityName}#ListDTO.class);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出#{tableInfo}#")
    public ResponseEntity<byte[]> export(@RequestBody @Valid #{UpEntityName}#QueryDTO queryDTO) throws IOException, IllegalAccessException {
        List<#{UpTableName}#> all = #{EntityName}#Mapper.listPage(queryDTO);
        String fileName = "#{UpEntityName}#"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        return ExcelUtil.generateImportFile(DtoMapper.convertList(all, #{UpEntityName}#ListDTO.class), fileName, #{UpEntityName}#ListDTO.class);
    }

    @Operation(summary = "新增活更新#{tableInfo}#")
    @PostMapping("/save")
    @Log("新增|修改#{tableInfo}#")
    public void save(@RequestBody @Valid #{UpEntityName}#DTO #{EntityName}#DTO){
        #{UpTableName}# #{TableName}# = DtoMapper.convert(#{EntityName}#DTO, #{UpTableName}#.class);
        #{EntityName}#Repository.save(#{TableName}#);
    }

    @Operation(summary = "查询全部#{tableInfo}#")
    @GetMapping("/list")
    public List<#{UpEntityName}#ListDTO> list(){
        List<#{UpTableName}#> all = #{EntityName}#Repository.findAll();
        return DtoMapper.convertList(all, #{UpEntityName}#ListDTO.class);
    }

    @Operation(summary = "查询")
    @GetMapping("/find/{id}")
    public #{UpEntityName}#DTO find(@PathVariable Integer id){
        #{UpTableName}# one = #{EntityName}#Repository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, #{UpEntityName}#DTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除#{tableInfo}#")
    public void delete(@PathVariable Integer id) {
        #{EntityName}#Repository.deleteById(id);
    }

}