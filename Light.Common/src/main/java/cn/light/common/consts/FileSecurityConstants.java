package cn.light.common.consts;

import java.util.Arrays;
import java.util.List;

/**
 * 文件上传安全常量（Light.Common 模块，Server 与 Admin 均可使用）
 */
public interface FileSecurityConstants {

    /** 允许的文件扩展名白名单（小写，不含点） */
    List<String> ALLOWED_EXTENSIONS = Arrays.asList(
            "png", "jpg", "jpeg", "gif", "bmp", "webp",
            "pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx",
            "txt", "csv", "md", "zip", "rar", "7z"
    );

    /** 单文件最大大小（字节），默认 10MB */
    long MAX_FILE_SIZE = 10 * 1024 * 1024L;
}
