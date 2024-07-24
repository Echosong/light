package  cn.light.server.service;

import cn.light.entity.entity.SysDictType;
import cn.light.packet.dto.dictType.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * 自动生成 字典类型 service 接口
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-07-24 22:17:47
 */
public interface DictTypeService extends IService<SysDictType> {

    Page<DictTypeListDTO> listPage(DictTypeQueryDTO queryDTO);

    ResponseEntity<byte[]> export(DictTypeQueryDTO queryDTO);

    DictTypeDTO save(DictTypeDTO dictTypeDTO);

    DictTypeDTO find(Integer id);

    void delete(Integer id);
    

}