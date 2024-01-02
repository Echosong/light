package cn.light.server.service;


import cn.light.packet.dto.config.ConfigDTO;
import cn.light.entity.entity.KdConfig;

import java.util.List;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/28 002817:19</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
public interface ConfigService {
    /**
     * 获取一组配置数据
     *
     * @param group 分组没加
     * @param key 键名
     * @return
     */
    ConfigDTO getByGroupAndKey(Integer group, String key);

    /**
     * 拿到全部配置缓存起来
     * @return
     */
    List<ConfigDTO> listAll();

    /**
     * 修改保持配置
     * @param kdConfig
     */
    void save(KdConfig kdConfig);

    void update();
}
