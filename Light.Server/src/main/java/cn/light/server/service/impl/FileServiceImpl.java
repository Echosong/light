package cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.light.entity.entity.SysFile;
import cn.light.entity.repository.FileRepository;
import cn.light.packet.dto.config.ConfigDTO;
import cn.light.packet.enums.ConfigGroupEnum;
import cn.light.packet.enums.StorageTypeEnum;
import cn.light.server.service.ConfigService;
import cn.light.server.service.FileService;
import cn.light.server.service.StorageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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
public class FileServiceImpl implements FileService {

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
}
