package cn.light.server.service;

import cn.light.packet.dto.file.FileDTO;
import cn.light.packet.dto.file.FileQueryDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 图片上传
 * @author : 二胡子
 * @version :1.0.0
 */
public interface FileService {

     Map<String, String> uploadFile(MultipartFile file, String params);

    ResponseEntity download(String uuid);

    void delete(Integer id);

    void save(FileDTO fileDTO);

    Page<FileDTO> listPage(FileQueryDTO fileQueryDTO);
}
