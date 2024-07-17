package  cn.light.server.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.light.entity.entity.SysResourceCategory;
import cn.light.entity.mapper.ResourceCategoryMapper;
import cn.light.entity.repository.ResourceCategoryRepository;
import cn.light.server.service.ResourceCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 自动生成 资源分类 service 实现
 * @author : 二胡子
 * @date : 2024-07-17 23:06:08
 */
@Service
public class ResourceCategoryServiceImpl extends ServiceImpl<ResourceCategoryMapper, SysResourceCategory> implements ResourceCategoryService {
        @Resource
        private  ResourceCategoryRepository resourceCategoryRepository;

}