package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.resource.ResourceQueryDTO;
import cn.light.entity.entity.SysResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 办公资源文件存在
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface ResourceMapper extends BaseMapper<SysResource> {
    List<SysResource> listPage(ResourceQueryDTO resourceQueryDTO);
}
