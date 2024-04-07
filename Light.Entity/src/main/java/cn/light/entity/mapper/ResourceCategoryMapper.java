package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.resourceCategory.ResourceCategoryQueryDTO;
import cn.light.entity.entity.SysResourceCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 资源分类
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface ResourceCategoryMapper extends BaseMapper<SysResourceCategory> {
    List<SysResourceCategory> listPage(ResourceCategoryQueryDTO resourceCategoryQueryDTO);
}
