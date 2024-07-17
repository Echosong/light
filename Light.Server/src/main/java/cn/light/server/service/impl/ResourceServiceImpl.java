package  cn.light.server.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.light.entity.entity.SysResource;
import cn.light.entity.mapper.ResourceMapper;
import cn.light.entity.repository.ResourceRepository;
import cn.light.server.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 自动生成 办公资源文件存在 service 实现
 * @author : 二胡子
 * @date : 2024-07-17 23:06:08
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, SysResource> implements ResourceService {
        @Resource
        private  ResourceRepository resourceRepository;

}