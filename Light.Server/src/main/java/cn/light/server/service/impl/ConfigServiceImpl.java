package  cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.ExcelUtil;
import cn.light.common.util.PageUtil;

import cn.light.packet.enums.system.ConfigGroupEnum;
import org.springframework.stereotype.Service;
import cn.light.entity.entity.SysConfig;
import cn.light.entity.mapper.ConfigMapper;
import cn.light.server.service.ConfigService;
import cn.light.packet.dto.config.*;

import org.springframework.http.ResponseEntity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 自动生成 系统配置 service 实现
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-07-24 22:40:25
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, SysConfig> implements ConfigService {

    @Override
    public Page<ConfigListDTO> listPage(ConfigQueryDTO queryDTO){
         Page<SysConfig> dataPages  =  PageUtil.getPage(this.baseMapper::listPage, queryDTO);
         return DtoMapper.convertPage(dataPages, ConfigListDTO.class);
    }

    @Override
    public ResponseEntity<byte[]> export(ConfigQueryDTO queryDTO) {
        List<SysConfig> all = this.baseMapper.listPage(queryDTO);
        String fileName = "Config"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        try {
            return ExcelUtil.generateImportFile(DtoMapper.convertList(all, ConfigListDTO.class), fileName, ConfigListDTO.class);
        }catch (Exception e) {
            throw new BaseKnownException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ConfigDTO save(ConfigDTO saveDTO) {
        SysConfig config = DtoMapper.convert(saveDTO, SysConfig.class);
        this.saveOrUpdate(config);
        return DtoMapper.convert(config, ConfigDTO.class);
    }

    @Override
    public void delete(Integer id) {
        this.removeById(id);
    }

    @Override
    public ConfigDTO find(Integer id){
        SysConfig one = this.getOptById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, ConfigDTO.class);
    }

    @Override
    public ConfigDTO getByGroupAndKey(Integer group, String key) {
        List<ConfigDTO> kdConfigs = DtoMapper.convertList(this.list(), ConfigDTO.class);
        return kdConfigs.stream()
                .filter(t-> Objects.equals(group, t.getGroup()) && Objects.equals(t.getKey(), key))
                .findFirst()
                .orElse(new ConfigDTO());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveList(List<ConfigDTO> configDTOList) {
        List<SysConfig> configs = DtoMapper.convertList(configDTOList, SysConfig.class);
        this.saveOrUpdateBatch(configs);
    }

    @Override
    public Map<String, List<ConfigListDTO>> getConfigList() {
        List<SysConfig> all = this.list();
        List<ConfigListDTO> configLists = DtoMapper.convertList(all, ConfigListDTO.class);
        //根据 group 分组
        Map<Integer, List<ConfigListDTO>> collect = configLists.stream().collect(Collectors.groupingBy(ConfigListDTO::getGroup));
        Map<String, List<ConfigListDTO>> map = new HashMap<>();

        for (Map.Entry<Integer, List<ConfigListDTO>> integerListEntry : collect.entrySet()) {
            integerListEntry.getValue().forEach(item->{
                if(StrUtil.contains(item.getDescription(),"{")){
                    try {
                        JSONArray bean = JSONUtil.parseArray(item.getDescription());
                        item.setOptions(bean);
                    }catch (Exception ignored) {}
                }
            });
            Arrays.stream(ConfigGroupEnum.values()).filter(t -> Objects.equals(t.getCode(), integerListEntry.getKey())).findFirst()
                    .ifPresent(t -> map.put(t.getName(), integerListEntry.getValue()));
        }
        return map;
    }


}