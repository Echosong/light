package  cn.light.server.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.light.entity.entity.SysDictData;
import cn.light.entity.mapper.DictDataMapper;
import cn.light.entity.repository.DictDataRepository;
import cn.light.server.service.DictDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 自动生成 字典数据 service 实现
 * @author : 二胡子
 * @date : 2024-07-17 23:06:09
 */
@Service
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, SysDictData> implements DictDataService {
        @Resource
        private  DictDataRepository dictDataRepository;

}