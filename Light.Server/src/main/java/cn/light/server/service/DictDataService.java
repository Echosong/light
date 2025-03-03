package  cn.light.server.service;

import cn.light.entity.entity.SysDictData;
import cn.light.packet.dto.dictData.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * 自动生成 字典数据 service 接口
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-07-24 22:17:47
 */
public interface DictDataService extends IService<SysDictData> {

    Page<DictDataListDTO> listPage(DictDataQueryDTO queryDTO);

    ResponseEntity<byte[]> export(DictDataQueryDTO queryDTO);

    DictDataDTO save(DictDataDTO dictDataDTO);

    DictDataDTO find(Integer id);

    void delete(Integer id);
    

}