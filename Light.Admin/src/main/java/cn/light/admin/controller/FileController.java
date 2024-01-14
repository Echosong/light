package cn.light.admin.controller;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.light.admin.config.properties.FileUploadProperties;
import cn.light.common.annotation.ApiResultIgnore;
import cn.light.common.annotation.Log;
import cn.light.packet.dto.file.FileDTO;
import cn.light.packet.dto.file.FileQueryDTO;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.PageUtil;
import cn.light.entity.entity.SysFile;
import cn.light.entity.mapper.FileMapper;
import cn.light.entity.repository.FileRepository;

import cn.light.server.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * <p>Title: </p >
 * <p>Description: 文件管理管理</p >
 * <p>Company: http://www.hn1024.cn</p >
 * <p>create date: 2024-01-01 20:15:44</p >
 *
 * listPage,save,delete,find,list
 * @author : echosong
 * @version :1.0.0
 */
@Tag(name = "文件管理 控制器")
@RestController
@RequestMapping("/file")
public class FileController extends BaseController{
    private final FileUploadProperties fileUploadProperties;
    @Resource
    private  FileRepository fileRepository;

    @Resource
    private FileMapper fileMapper;

    @Resource
    private FileService fileService;
    @Autowired
    public FileController(FileUploadProperties fileUploadProperties) {
        this.fileUploadProperties = fileUploadProperties;
    }

    /**
     * 上传文件 整个系统其他关联地方可以考虑存文件或者uuid （），这样一来文件处理方便
     *
     * @param file     文件流
     * @param params   前端需要原路带回的参数
     * @throws IOException 错误
     */
    @PostMapping("/upload")
    @Operation(summary = "文件上传")
    public Map<String, String> upload(@RequestParam(value = "file", required = false) MultipartFile file,  String params) throws IOException {
        return fileService.uploadFile(file,  params);
    }

    /***
     * 上传时候配置的 地址，可以直接用当前地址
     * 使用这种下载好处是可以文件层面控制权限， 缺点浪费内存
     * @param uuid 唯一表示
     */
    @ApiResultIgnore
    @GetMapping({"/download/{uuid}"})
    @Operation(summary = "文件下载")
    public ResponseEntity download(@PathVariable String uuid) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + uuid);
        Optional<SysFile> byUrlPath = fileRepository.findByUuid(uuid);
        return byUrlPath.map(kdFile -> new ResponseEntity(FileUtil.readBytes(kdFile.getFilePath()), headers, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity(HttpStatus.MULTI_STATUS));

    }

    /**
     * 分页查询
     *
     * @param fileQueryDTO 是
     */
    @PutMapping("/listPage")
    @Operation(summary = "列表分页")
    public Page<FileDTO> list(@RequestBody FileQueryDTO fileQueryDTO) {
        Page<SysFile> files = PageUtil.getPage(fileMapper::listPage, fileQueryDTO);
        return DtoMapper.convertPage(files, FileDTO.class);
    }

    /**
     * 删除指定文件
     *
     * @param id 文件id
     */
    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除指定文件")
    public void delete(@PathVariable Integer id) {
        fileRepository.findById(id).ifPresent(t -> {
            if (FileUtil.del(t.getFilePath())) {
                fileRepository.delete(t);
            }
        });
    }

    @Operation(summary = "新增活更新日志")
    @PostMapping("/save")
    @Log("新增|修改日志")
    public void save(@RequestBody @Valid FileDTO fileDTO){
        SysFile file = DtoMapper.convert(fileDTO, SysFile.class);
        fileRepository.save(file);
    }

}