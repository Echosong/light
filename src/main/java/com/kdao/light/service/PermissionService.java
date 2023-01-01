package com.kdao.light.service;

import com.kdao.light.entity.KdPermission;

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
public interface PermissionService {
    /**
     * 根据角色获取角色下所有权限
     * @param roleIds
     * @return
     */
    List<KdPermission> getListByrole(List<Integer> roleIds);
}
