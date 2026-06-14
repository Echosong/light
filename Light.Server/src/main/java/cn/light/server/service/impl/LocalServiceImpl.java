package cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.packet.dto.config.ConfigDTO;
import cn.light.packet.enums.system.ConfigGroupEnum;
import cn.light.server.service.ConfigService;
import cn.light.server.service.StorageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 本地文件存储（安全版：防范路径穿越、校验文件名合法性）
 */
@Service(value = "storageService_10")
@Slf4j
public class LocalServiceImpl implements StorageService {

    @Resource
    private ConfigService configService;

    @Override
    public String uploadFile(MultipartFile file, String fileName) {
        ConfigDTO ossConfig = configService.getByGroupAndKey(ConfigGroupEnum.FILE.getCode(), "localConfig");
        JSONObject ossJson = JSONUtil.parseObj(ossConfig.getValue());
        String localPath = ossJson.getStr("localPath");
        String localUrl = ossJson.getStr("localUrl");

        if (StrUtil.isBlank(localPath) || StrUtil.isBlank(localUrl)) {
            throw new BaseKnownException(500, "文件存储配置缺失");
        }

        // 文件名安全校验：拒绝包含路径分隔符或父目录穿越字符
        if (fileName == null
                || fileName.contains("..")
                || fileName.contains("/")
                || fileName.contains("\\")
                || fileName.contains("\0")) {
            throw new BaseKnownException(400, "非法的文件名称");
        }

        try {
            String format = DateUtil.format(new Date(), "yyyy/MM/dd");
            File folder = FileServiceImpl.ensureUploadDir(localPath, format);
            File target = new File(folder, fileName);

            // 再次校验最终落点仍位于 base 目录内（canonicalPath 对比）
            if (!target.getCanonicalPath().startsWith(new File(localPath).getCanonicalPath())) {
                throw new IOException("文件存储路径非法：" + target.getAbsolutePath());
            }
            file.transferTo(target);

            // 仅返回相对 URL，避免泄漏服务器绝对路径
            return localUrl + format + "/" + fileName;
        } catch (IOException e) {
            log.error("文件上传失败", e);
            throw new BaseKnownException(500, "文件上传失败");
        }
    }
}
