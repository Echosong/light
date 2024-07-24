package  cn.light.server.service;

import cn.light.entity.entity.SysConfig;
import cn.light.packet.dto.config.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * 自动生成 系统配置 service 接口
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-07-24 22:40:25
 */
public interface ConfigService extends IService<SysConfig> {

    Page<ConfigListDTO> listPage(ConfigQueryDTO queryDTO);

    ResponseEntity<byte[]> export(ConfigQueryDTO queryDTO);

    ConfigDTO save(ConfigDTO configDTO);

    ConfigDTO find(Integer id);

    void delete(Integer id);


    ConfigDTO getByGroupAndKey(Integer group, String key);
}