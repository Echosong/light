package  cn.light.server.service;

import cn.light.entity.entity.SysAd;
import cn.light.packet.dto.ad.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * 自动生成 广告管理 service 接口
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-10-05 21:07:18
 */
public interface AdService extends IService<SysAd> {

    Page<AdListDTO> listPage(AdQueryDTO queryDTO);

    ResponseEntity<byte[]> export(AdQueryDTO queryDTO);

    AdDTO save(AdDTO adDTO);

    AdDTO find(Integer id);

    void delete(Integer id);
    

}