package cn.light.server.service;


import cn.light.entity.entity.SysRole;

import java.util.List;

/**
 * <p>Title: </p >
 * <p>Description: 角色</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/28 002816:39</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
public interface RoleService {
    /**
     * 根据用户获取当前的角色
     * @param userId
     * @return
     */
    List<SysRole> listByUser(Integer userId);
}
