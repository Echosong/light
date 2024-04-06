package cn.light.server.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 统一存储服务
 * @author 二胡子
 */
public interface StorageService {
    String uploadFile(MultipartFile file, String fileName);
}
