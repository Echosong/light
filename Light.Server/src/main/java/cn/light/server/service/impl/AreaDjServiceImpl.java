package  cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.ExcelUtil;
import cn.light.common.util.PageUtil;

import org.springframework.stereotype.Service;
import cn.light.entity.entity.SysAreaDj;
import cn.light.entity.mapper.AreaDjMapper;
import cn.light.server.service.AreaDjService;
import cn.light.packet.dto.areaDj.*;

import org.springframework.http.ResponseEntity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.data.domain.Page;

import java.util.*;

/**
 * 自动生成 行政区划实体 service 实现
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-10-05 21:10:39
 */
@Service
public class AreaDjServiceImpl extends ServiceImpl<AreaDjMapper, SysAreaDj> implements AreaDjService {

    @Override
    public Page<AreaDjListDTO> listPage(AreaDjQueryDTO queryDTO){
         Page<SysAreaDj> dataPages  =  PageUtil.getPage(this.baseMapper::listPage, queryDTO);
         return DtoMapper.convertPage(dataPages, AreaDjListDTO.class);
    }

    @Override
    public ResponseEntity<byte[]> export(AreaDjQueryDTO queryDTO) {
        List<SysAreaDj> all = this.baseMapper.listPage(queryDTO);
        String fileName = "AreaDj"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        try {
            return ExcelUtil.generateImportFile(DtoMapper.convertList(all, AreaDjListDTO.class), fileName, AreaDjListDTO.class);
        }catch (Exception e) {
            throw new BaseKnownException(e.getMessage());
        }
    }

    @Override
    public AreaDjDTO save(AreaDjDTO saveDTO) {
        SysAreaDj areaDj = DtoMapper.convert(saveDTO, SysAreaDj.class);
        this.saveOrUpdate(areaDj);
        return DtoMapper.convert(areaDj, AreaDjDTO.class);
    }

    @Override
    public void delete(Integer id) {
        this.removeById(id);
    }

    @Override
    public AreaDjDTO find(Integer id){
        SysAreaDj one = Optional.of(id).map(this::getById)
                             .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, AreaDjDTO.class);
    }

    
    @Override
    public List<Map<String, Object>> getMap(){
         List<SysAreaDj> all = this.baseMapper.selectList(new LambdaQueryWrapper<SysAreaDj>()
                        .select(SysAreaDj::getId, SysAreaDj::getAreaName)
                        .orderByDesc(SysAreaDj::getId)
                );
        List<Map<String, Object>> maps = new ArrayList<>();
        for (SysAreaDj item : all) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("name", item.getAreaName());
            maps.add(map);
        }
        return maps;
    }
    


}