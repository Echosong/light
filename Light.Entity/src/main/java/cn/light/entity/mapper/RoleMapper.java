package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.common.dto.role.RoleQueryDTO;
import cn.light.entity.entity.KdRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 所属客户
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface RoleMapper extends BaseMapper<KdRole> {
    List<KdRole> listPage(RoleQueryDTO roleQueryDTO);
}
