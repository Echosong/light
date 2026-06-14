package cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.light.common.consts.FileSecurityConstants;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.PageUtil;
import cn.light.entity.entity.SysFile;
import cn.light.entity.mapper.FileMapper;
import cn.light.packet.dto.config.ConfigDTO;
import cn.light.packet.dto.file.FileDTO;
import cn.light.packet.dto.file.FileQueryDTO;
import cn.light.packet.enums.system.ConfigGroupEnum;
import cn.light.packet.enums.system.StorageTypeEnum;
import cn.light.server.service.ConfigService;
import cn.light.server.service.FileService;
import cn.light.server.service.StorageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 文件上传服务（安全版）
 * - 白名单扩展名校验
 * - 文件大小限制校验
 * - 安全文件名（UUID + 扩展名），防范路径穿越
 */
@Service
@Slf4j
public class FileServiceImpl extends ServiceImpl<FileMapper, SysFile> implements FileService {

    @Resource
    private ConfigService configService;

    /**
     * 获取当前配置的存储服务类型
     */
    private String getStorageService() {
        String storageKey = "storageService";
        try {
            ConfigDTO storageType = configService.getByGroupAndKey(ConfigGroupEnum.FILE.getCode(), storageKey);
            Integer storageService = Integer.parseInt(storageType.getValue());
            StorageTypeEnum typeEnum = Arrays.stream(StorageTypeEnum.values())
                    .filter(t -> Objects.equals(storageService, t.getCode()))
                    .findFirst()
                    .orElse(StorageTypeEnum.LOCAL);
            return storageKey + "_" + typeEnum.getCode();
        } catch (Exception e) {
            log.warn("获取存储服务配置失败，回退为本地存储", e);
            return storageKey + "_" + StorageTypeEnum.LOCAL.getCode();
        }
    }

    @Override
    public Map<String, String> uploadFile(MultipartFile file, String params) {
        // 1. 非空校验
        if (file == null || file.isEmpty()) {
            throw new BaseKnownException(400, "上传文件不能为空");
        }

        // 2. 文件大小限制校验
        long fileSize = file.getSize();
        if (fileSize > FileSecurityConstants.MAX_FILE_SIZE) {
            throw new BaseKnownException(400,
                    "文件大小超出限制，最大 " + (FileSecurityConstants.MAX_FILE_SIZE / 1024 / 1024) + " MB");
        }

        // 3. 安全提取并校验扩展名
        String originalFilename = file.getOriginalFilename();
        if (StrUtil.isBlank(originalFilename)
                || originalFilename.contains("..")
                || originalFilename.contains("/")
                || originalFilename.contains("\\")) {
            throw new BaseKnownException(400, "非法的文件名");
        }

        String extName = FileUtil.extName(originalFilename);
        if (StrUtil.isBlank(extName)) {
            throw new BaseKnownException(400, "文件缺少扩展名");
        }
        extName = extName.toLowerCase();
        if (!FileSecurityConstants.ALLOWED_EXTENSIONS.contains(extName)) {
            throw new BaseKnownException(400, "不允许的文件类型：" + extName);
        }

        // 4. 生成安全的存储文件名（UUID + 合法扩展名）
        String uuid = IdUtil.fastSimpleUUID();
        String safeFileName = uuid + "." + extName;

        // 5. 调用底层存储服务（本地/OSS 等）
        StorageService storageService = SpringUtil.getBean(getStorageService(), StorageService.class);
        String filePath = storageService.uploadFile(file, safeFileName);

        // 6. 入库
        Map<String, String> map = new HashMap<>(4);
        map.put("name", originalFilename);
        map.put("url", filePath);
        map.put("params", params);
        map.put("uuid", uuid);

        SysFile sysFile = new SysFile();
        sysFile.setFilePath(filePath);
        sysFile.setFileName(FileUtil.mainName(originalFilename));
        sysFile.setFileSize(fileSize);
        sysFile.setExtend(extName);
        sysFile.setFileType(1);
        sysFile.setUuid(uuid);
        sysFile.setUrlPath(DateUtil.format(new Date(), "yyyy/MM/dd") + "/" + safeFileName);
        this.saveOrUpdate(sysFile);
        map.put("fileId", sysFile.getId().toString());
        return map;
    }

    @Override
    public ResponseEntity<byte[]> download(String uuid) {
        if (StrUtil.isBlank(uuid)) {
            return ResponseEntity.badRequest().build();
        }
        SysFile file = this.baseMapper.selectOne(
                new LambdaQueryWrapper<SysFile>().eq(SysFile::getUuid, uuid)
        );
        return Optional.ofNullable(file)
                .map(f -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    String safeDownloadName = f.getUuid() + "." + f.getExtend();
                    headers.setContentDispositionFormData("attachment", safeDownloadName);
                    return new ResponseEntity<>(FileUtil.readBytes(f.getFilePath()), headers, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            return;
        }
        this.getOptById(id).ifPresent(t -> {
            if (FileUtil.del(t.getFilePath())) {
                this.removeById(t);
            }
        });
    }

    @Override
    public void save(FileDTO fileDTO) {
        SysFile file = DtoMapper.convert(fileDTO, SysFile.class);
        this.saveOrUpdate(file);
    }

    @Override
    public Page<FileDTO> listPage(FileQueryDTO fileQueryDTO) {
        Page<SysFile> files = PageUtil.getPage(this.baseMapper::listPage, fileQueryDTO);
        return DtoMapper.convertPage(files, FileDTO.class);
    }

    /**
     * 确保上传目录安全创建（防范路径穿越）
     */
    public static File ensureUploadDir(String baseFolder, String datePath) throws IOException {
        File folder = new File(baseFolder, datePath);
        if (!folder.isDirectory() && !folder.mkdirs()) {
            throw new IOException("无法创建上传目录：" + folder.getAbsolutePath());
        }
        // canonicalPath 对比，防止 ../ 路径穿越
        if (!folder.getCanonicalPath().startsWith(new File(baseFolder).getCanonicalPath())) {
            throw new IOException("上传路径非法：" + folder.getAbsolutePath());
        }
        return folder;
    }
}
