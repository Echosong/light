package com.kdao.light.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.setting.Setting;
import com.kdao.light.common.dto.config.ConfigDTO;
import com.kdao.light.common.exception.BaseKnownException;
import com.kdao.light.common.utils.DtoMapper;
import com.kdao.light.entity.KdConfig;
import com.kdao.light.repository.ConfigRepository;
import com.kdao.light.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/28 002817:22</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Service
@Slf4j
public class ConfigServiceImpl implements ConfigService {

    private final ConfigRepository configRepository;


    @Autowired
    public ConfigServiceImpl(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    /**
     * 获取一组配置数据
     *
     * @param group 分组没加
     * @param key 键名
     */
    @Override
    public ConfigDTO getByGroupAndKey(Integer group, String key) {
        List<ConfigDTO> kdConfigs = this.listAll();
        return kdConfigs.stream()
                .filter(t-> Objects.equals(group, t.getGroup()) && Objects.equals(t.getKey(), key))
                .findFirst()
                .orElse(new ConfigDTO());
    }


    /**
     * 查询全部进行缓存
     */
    @Override
    //@Cacheable(value = "sysConfigCacheAll6")
    public List<ConfigDTO> listAll() {
        return DtoMapper.convertList(configRepository.findAll(), ConfigDTO.class);
    }

    /**
     * 新增修改配置
     * @param kdConfig
     */
    @Override
    @CacheEvict(value = "sysConfig", allEntries = true)
    public void save(KdConfig kdConfig) {
        configRepository.save(kdConfig);
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public void update() {
        KdConfig config = configRepository.findById(2)
                .orElseThrow(()->new BaseKnownException(500, ""));

        config.setName("0031dsfsd");
        config.setValue("xxxxx");
        config.setKey("1001");

        configRepository.save(config);

        Console.log(config);

        String a = "1650.0";
        try {
            DateUtil.parse(a);
        }catch (Exception ignored){}

        List<KdConfig> configList = configRepository.findAll();

        KdConfig config1 = configRepository.findById(1)
                .orElseThrow(()->new BaseKnownException(500, ""));

        for (KdConfig kdConfig : configList) {
            kdConfig.setValue("bbbbbb");
        }

      if(configList.get(0) == config){
           Console.log("相同");
           Console.log(config.hashCode() +"========="+ configList.get(0).hashCode());
       }else{
           Console.log(config.hashCode() +"========="+ configList.get(0).hashCode());
           Console.log(config.getKey()+"========="+ configList.get(0).getKey());
       }

        if(config1 == config){
            Console.log("相同");
            Console.log(config.hashCode() +"========="+ config1);
        }else{
            Console.log(config.hashCode() +"========="+ config1.hashCode());
            Console.log(config.getKey()+"========="+ config1.getKey());
        }
    }

}
