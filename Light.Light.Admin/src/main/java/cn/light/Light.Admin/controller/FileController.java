package cn.light.Light.Admin.controller;
import cn.light.common.annotation.Log;
import cn.light.common.dto.file.FileDTO;
import cn.light.common.dto.file.FileListDTO;
import cn.light.common.dto.file.FileQueryDTO;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.utils.DtoMapper;
import cn.light.common.utils.PageUtil;
import cn.light.entity.entity.KdFile;
import cn.light.entity.mapper.FileMapper;
import cn.light.entity.repository.FileRepository;

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
 * <p>Description: 文件管理管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 18:55:05</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "文件管理 控制器")
@RestController
@RequestMapping("/file")
public class FileController extends BaseController{
    @Resource
    private  FileRepository fileRepository;

    @Resource
    private FileMapper fileMapper;

    @Operation(summary = "分页查询文件管理")
    @PutMapping("/listPage")
    public Page<FileListDTO> listPage(@RequestBody @Valid FileQueryDTO queryDTO){
        Page<KdFile> dataPages  =  PageUtil.getPage(fileMapper::listPage, queryDTO);
        return DtoMapper.convertPage(dataPages, FileListDTO.class);
    }

    @Operation(summary = "新增活更新文件管理")
    @PostMapping("/save")
    @Log("新增|修改文件管理")
    public void save(@RequestBody @Valid FileDTO fileDTO){
        KdFile kdFile = DtoMapper.convert(fileDTO, KdFile.class);
        fileRepository.save(kdFile);
    }

    @Operation(summary = "查询全部文件管理")
    @GetMapping("/list")
    public List<FileListDTO> list(){
        List<KdFile> all = fileRepository.findAll();
        return DtoMapper.convertList(all, FileListDTO.class);
    }

    @Operation(summary = "查询")
    @GetMapping("/find/{id}")
    public FileDTO find(@PathVariable Integer id){
        KdFile one = fileRepository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, FileDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除")
    @Log("删除文件管理")
    public void delete(@PathVariable Integer id) {
        fileRepository.deleteById(id);
    }

}