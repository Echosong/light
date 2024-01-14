package cn.light.server.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 图片上传
 * @author : 二胡子
 * @version :1.0.0
 */
public interface FileService {

     Map<String, String> uploadFile(MultipartFile file, String params);
}
