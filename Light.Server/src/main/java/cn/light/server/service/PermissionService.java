package cn.light.server.service;



import cn.light.entity.entity.SysPermission;
import cn.light.entity.mapper.PermissionMapper;
import cn.light.packet.dto.permission.PermissionDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/28 002815:38</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
public interface PermissionService extends IService<SysPermission> {

    List<SysPermission> getListByrole(List<Integer> roleIds);

    List<PermissionDTO> listByUser(Integer userId);
}
