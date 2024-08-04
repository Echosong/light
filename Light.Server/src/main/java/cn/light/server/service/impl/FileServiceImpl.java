package cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.PageUtil;
import cn.light.entity.entity.SysFile;
import cn.light.entity.mapper.FileMapper;
import cn.light.entity.repository.FileRepository;
import cn.light.packet.dto.config.ConfigDTO;
import cn.light.packet.dto.file.FileDTO;
import cn.light.packet.dto.file.FileQueryDTO;
import cn.light.packet.enums.system.ConfigGroupEnum;
import cn.light.packet.enums.system.StorageTypeEnum;
import cn.light.server.service.ConfigService;
import cn.light.server.service.FileService;
import cn.light.server.service.StorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * 存储服务
 * @author : 二胡子
 * @version :1.0.0
 */
@Service
@Slf4j
public class FileServiceImpl  extends ServiceImpl<FileMapper, SysFile> implements FileService {

    @Resource
    private FileRepository fileRepository;
    @Resource
    private ConfigService configService;

    /**
     * 获取当前配置的服务类型
     * @return 配置
     */
    private String getStorageService(){
        String storageKey = "storageService";
        StorageTypeEnum storageTypeEnum = StorageTypeEnum.LOCAL;
        try {
            ConfigDTO storageType = configService.getByGroupAndKey(ConfigGroupEnum.FILE.getCode(), storageKey);
            Integer storageService = Integer.parseInt(storageType.getValue());
            storageTypeEnum = Arrays.stream(StorageTypeEnum.values())
                    .filter(t -> Objects.equals(storageService, t.getCode())).findFirst()
                    .orElse(StorageTypeEnum.LOCAL);
        }catch (Exception e) {
            log.error("获取存储服务失败", e);
        }
        return  storageKey+"_"+storageTypeEnum.getCode();
    }

    @Override
    public Map<String, String> uploadFile(MultipartFile file, String params) {
        String format = DateUtil.format(new Date(), "yyyy/MM/dd");

        long fileSize = file.getSize();
        //把可运行的文件屏蔽掉，免得 xss
        String uuid = IdUtil.fastUUID();
        String fileName = file.getOriginalFilename();
        String extName = FileUtil.extName(file.getOriginalFilename());
        List<String> limtExtName = Arrays.asList("html", "htm", "js");
        Assert.isTrue(!limtExtName.contains(extName), "文件格式不允许！");
        String newFilename = uuid + "." + extName;

        StorageService storageService = SpringUtil.getBean(getStorageService(), StorageService.class);
        String filePath = storageService.uploadFile(file, newFilename);

        Map<String, String> map = new HashMap<>(2);
        map.put("name", fileName);
        map.put("url", filePath);
        map.put("params", params);
        map.put("uuid", uuid);
        //入库
        SysFile kdFile = new SysFile();
        kdFile.setFilePath(filePath);
        kdFile.setFileName(FileUtil.mainName(fileName));
        kdFile.setFileSize(fileSize);
        kdFile.setExtend(extName);
        kdFile.setFileType(1);
        kdFile.setUrlPath(format + "/" + newFilename);
        SysFile save = fileRepository.save(kdFile);
        map.put("fileId", save.getId().toString());
        return map;
    }

    @Override
    public ResponseEntity download(String uuid) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + uuid);
        Optional<SysFile> byUrlPath = fileRepository.findByUuid(uuid);
        return byUrlPath.map(kdFile -> new ResponseEntity(FileUtil.readBytes(kdFile.getFilePath()), headers, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity(HttpStatus.MULTI_STATUS));
    }

    @Override
    public void delete(Integer id) {
        fileRepository.findById(id).ifPresent(t -> {
            if (FileUtil.del(t.getFilePath())) {
                fileRepository.delete(t);
            }
        });
    }

    @Override
    public void save(FileDTO fileDTO) {
        SysFile file = DtoMapper.convert(fileDTO, SysFile.class);
        fileRepository.save(file);
    }

    @Override
    public Page<FileDTO> listPage(FileQueryDTO fileQueryDTO) {
        Page<SysFile> files = PageUtil.getPage(this.baseMapper::listPage, fileQueryDTO);
        return DtoMapper.convertPage(files, FileDTO.class);
    }
}
