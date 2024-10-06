package  cn.light.server.service;

import cn.light.entity.entity.SysAreaDj;
import cn.light.packet.dto.areaDj.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * 自动生成 行政区划实体 service 接口
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-10-05 21:10:39
 */
public interface AreaDjService extends IService<SysAreaDj> {

    Page<AreaDjListDTO> listPage(AreaDjQueryDTO queryDTO);

    ResponseEntity<byte[]> export(AreaDjQueryDTO queryDTO);

    AreaDjDTO save(AreaDjDTO areaDjDTO);

    AreaDjDTO find(Integer id);

    void delete(Integer id);
    
    List<Map<String, Object>> getMap();
    

}