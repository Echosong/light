package  cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.ExcelUtil;
import cn.light.common.util.PageUtil;

import org.springframework.stereotype.Service;
import cn.light.entity.entity.SysAd;
import cn.light.entity.mapper.AdMapper;
import cn.light.server.service.AdService;
import cn.light.packet.dto.ad.*;

import org.springframework.http.ResponseEntity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.data.domain.Page;

import java.util.*;

/**
 * 自动生成 广告管理 service 实现
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-10-05 21:07:18
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, SysAd> implements AdService {

    @Override
    public Page<AdListDTO> listPage(AdQueryDTO queryDTO){
         Page<SysAd> dataPages  =  PageUtil.getPage(this.baseMapper::listPage, queryDTO);
         return DtoMapper.convertPage(dataPages, AdListDTO.class);
    }

    @Override
    public ResponseEntity<byte[]> export(AdQueryDTO queryDTO) {
        List<SysAd> all = this.baseMapper.listPage(queryDTO);
        String fileName = "Ad"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        try {
            return ExcelUtil.generateImportFile(DtoMapper.convertList(all, AdListDTO.class), fileName, AdListDTO.class);
        }catch (Exception e) {
            throw new BaseKnownException(e.getMessage());
        }
    }

    @Override
    public AdDTO save(AdDTO saveDTO) {
        SysAd ad = DtoMapper.convert(saveDTO, SysAd.class);
        this.saveOrUpdate(ad);
        return DtoMapper.convert(ad, AdDTO.class);
    }

    @Override
    public void delete(Integer id) {
        this.removeById(id);
    }

    @Override
    public AdDTO find(Integer id){
        SysAd one = Optional.of(id).map(this::getById)
                             .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, AdDTO.class);
    }

    


}