package cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.packet.dto.config.ConfigDTO;
import cn.light.packet.enums.ConfigGroupEnum;
import cn.light.packet.enums.FileTypeEnum;
import cn.light.server.service.ConfigService;
import cn.light.server.service.FileService;
import cn.light.server.service.StorageService;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * Oss 上传文件
 * @author : 二胡子
 * @version :1.0.0
 */
@Service(value = "storageService_20")
public class OssServiceImpl implements StorageService {

    @Resource
    private ConfigService configService;
    @Override
    public String uploadFile(MultipartFile file, String fileName) {
        ConfigDTO ossConfig = configService.getByGroupAndKey(ConfigGroupEnum.FILE.getCode(), "ossConfig");
        JSONObject ossJson = JSONUtil.parseObj(ossConfig.getValue());

        String endpoint = ossJson.getStr("aliyun_endpoint"); ;
        String accessKeyId = ossJson.getStr("aliyun_accessKeyId");
        String secretAccessKey = ossJson.getStr("aliyun_accessKeySecret");
        String bucketName = ossJson.getStr("aliyun_file_bucket");
        String ossUrl = ossJson.getStr("aliyun_origin_file_url");

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, secretAccessKey);
        try {
            String format = DateUtil.format(new Date(), "yyyy/MM/dd");
            fileName = format + "/" + fileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file.getInputStream());
            ossClient.putObject(putObjectRequest);
            return "https://" +ossUrl+"/"+ fileName;
        } catch (IOException e) {

            throw new BaseKnownException(e.getMessage());
        }
    }
}
