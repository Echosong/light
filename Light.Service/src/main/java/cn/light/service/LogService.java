package cn.light.service;



import cn.light.common.dto.log.LogDTO;
import cn.light.entity.entity.KdUser;

import java.util.List;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2022-11-10 21:54</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
public interface LogService {
    /**
     * 插入信息
     * @param logDTO 日志
     */
    void save(LogDTO logDTO);

     List<KdUser> getUserList();

     void  setUserList();


}
