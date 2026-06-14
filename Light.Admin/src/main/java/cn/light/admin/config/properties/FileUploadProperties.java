package cn.light.admin.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

/**
 * 文件上传安全配置
 * @author Nobita
 * @date 2020/4/18
 */
@Data
@ConfigurationProperties(prefix = "file")
public class FileUploadProperties {

    private String accessPathPattern = "/u/**";
    private String uploadFolder;
    private String accessPrefixUrl = "/u/";
    private String url;

    /** 允许的文件扩展名白名单（小写，不含点） */
    private List<String> allowedExtensions = Arrays.asList(
            "png", "jpg", "jpeg", "gif", "bmp", "webp",
            "pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx",
            "txt", "csv", "md", "zip", "rar", "7z"
    );

    /** 单文件最大大小（字节），默认 10MB */
    private long maxFileSize = 10 * 1024 * 1024L;
}
