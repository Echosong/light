package com.kdao.light.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kdao.light.common.dto.permission.PermissionQueryDTO;
import com.kdao.light.entity.KdPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>Title: light</p >
 * <p>Description: PermissionMapper</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2023/12/28 16:59 </p >
 *
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface PermissionMapper extends BaseMapper<KdPermission> {
    List<KdPermission> listPage(PermissionQueryDTO permissionQueryDTO);
}
