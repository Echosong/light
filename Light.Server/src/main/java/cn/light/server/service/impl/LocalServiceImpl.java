package cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.packet.dto.config.ConfigDTO;
import cn.light.packet.enums.system.ConfigGroupEnum;
import cn.light.server.service.ConfigService;
import cn.light.server.service.StorageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 本地文件存在
 * @author : 二胡子
 * @version :1.0.0
 */
@Service(value = "storageService_10")
public class LocalServiceImpl implements  StorageService{
    @Resource
    private ConfigService configService;
    @Override
    public String uploadFile(MultipartFile file, String fileName) {
        ConfigDTO ossConfig = configService.getByGroupAndKey(ConfigGroupEnum.ADMIN.getCode(), "localConfig");
        JSONObject ossJson = JSONUtil.parseObj(ossConfig.getValue());
        String localPath = ossJson.getStr("localPath");
        //注意这里，可以是后台地址的一个子目录
        String localUrl = ossJson.getStr("localUrl");

        try {
            String format = DateUtil.format(new Date(), "yyyy/MM/dd");
            File folder = new File(localPath + format);
            if (!folder.isDirectory()) {
                folder.mkdirs();
            }
            file.transferTo(new File(folder, fileName));
            return localUrl + folder +"/"+ fileName;
        } catch (IOException e) {

            throw new BaseKnownException(e.getMessage());
        }
    }
}
