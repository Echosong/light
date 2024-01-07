package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.user.UserQueryDTO;
import cn.light.entity.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 用户
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<SysUser> {
    List<SysUser> listPage(UserQueryDTO userQueryDTO);
}
