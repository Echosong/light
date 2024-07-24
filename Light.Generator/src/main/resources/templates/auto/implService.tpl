package  #{SYS_PATH}.server.service.impl;

import cn.hutool.core.date.DateUtil;
import #{SYS_PATH}.common.exception.BaseKnownException;
import #{SYS_PATH}.common.util.DtoMapper;
import #{SYS_PATH}.common.util.ExcelUtil;
import #{SYS_PATH}.common.util.PageUtil;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import #{SYS_PATH}.entity.entity.#{UpTableName}#;
import #{SYS_PATH}.entity.mapper.#{UpEntityName}#Mapper;
import #{SYS_PATH}.entity.repository.#{UpEntityName}#Repository;
import #{SYS_PATH}.server.service.#{UpEntityName}#Service;
import #{SYS_PATH}.packet.dto.#{EntityName}#.*;

import org.springframework.http.ResponseEntity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.data.domain.Page;

import java.util.*;

/**
 * 自动生成 #{tableInfo}# service 实现
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : #{localDate}#
 */
@Service
public class #{UpEntityName}#ServiceImpl extends ServiceImpl<#{UpEntityName}#Mapper, #{UpTableName}#> implements #{UpEntityName}#Service {
    @Resource
    private  #{UpEntityName}#Repository #{EntityName}#Repository;

    @Override
    public Page<#{UpEntityName}#ListDTO> listPage(#{UpEntityName}#QueryDTO queryDTO){
         Page<#{UpTableName}#> dataPages  =  PageUtil.getPage(this.baseMapper::listPage, queryDTO);
         return DtoMapper.convertPage(dataPages, #{UpEntityName}#ListDTO.class);
    }

    @Override
    public ResponseEntity<byte[]> export(#{UpEntityName}#QueryDTO queryDTO) {
        List<#{UpTableName}#> all = this.baseMapper.listPage(queryDTO);
        String fileName = "#{UpEntityName}#"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        try {
            return ExcelUtil.generateImportFile(DtoMapper.convertList(all, #{UpEntityName}#ListDTO.class), fileName, #{UpEntityName}#ListDTO.class);
        }catch (Exception e) {
            throw new BaseKnownException(e.getMessage());
        }
    }

    @Override
    public #{UpEntityName}#DTO save(#{UpEntityName}#DTO saveDTO) {
        #{UpTableName}# #{EntityName}# = DtoMapper.convert(saveDTO, #{UpTableName}#.class);
        #{EntityName}#Repository.save(#{EntityName}#);
        return DtoMapper.convert(#{EntityName}#, #{UpEntityName}#DTO.class);
    }

    @Override
    public void delete(Integer id) {
        #{EntityName}#Repository.deleteById(id);
    }

    @Override
    public #{UpEntityName}#DTO find(Integer id){
        #{UpTableName}# one = #{EntityName}#Repository.findById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, #{UpEntityName}#DTO.class);
    }

    //start
    @Override
    public List<Map<String, Object>> getMap(){
         List<#{UpTableName}#> all = this.baseMapper.selectList(new LambdaQueryWrapper<#{UpTableName}#>()
                        .select(#{UpTableName}#::getId, #{UpTableName}#::get#{keyName}#)
                        .orderByDesc(#{UpTableName}#::getId)
                );
        List<Map<String, Object>> maps = new ArrayList<>();
        for (#{UpTableName}# item : all) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("name", item.get#{keyName}#());
            maps.add(map);
        }
        return maps;
    }
    //end


}