package  cn.light.server.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.light.entity.entity.SysDictType;
import cn.light.entity.mapper.DictTypeMapper;
import cn.light.entity.repository.DictTypeRepository;
import cn.light.server.service.DictTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 自动生成 字典类型 service 实现
 * @author : 二胡子
 * @date : 2024-07-17 23:06:09
 */
@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, SysDictType> implements DictTypeService {
        @Resource
        private  DictTypeRepository dictTypeRepository;

}