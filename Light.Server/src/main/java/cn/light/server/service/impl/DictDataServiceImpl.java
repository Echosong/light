package  cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.ExcelUtil;
import cn.light.common.util.PageUtil;
import cn.light.entity.entity.SysDictData;
import cn.light.entity.mapper.DictDataMapper;
import cn.light.packet.dto.dictData.DictDataDTO;
import cn.light.packet.dto.dictData.DictDataListDTO;
import cn.light.packet.dto.dictData.DictDataQueryDTO;
import cn.light.server.service.DictDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 自动生成 字典数据 service 实现
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-07-24 22:17:47
 */
@Service
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, SysDictData> implements DictDataService {


    @Override
    public Page<DictDataListDTO> listPage(DictDataQueryDTO queryDTO){
         Page<SysDictData> dataPages  =  PageUtil.getPage(this.baseMapper::listPage, queryDTO);
         return DtoMapper.convertPage(dataPages, DictDataListDTO.class);
    }

    @Override
    public ResponseEntity<byte[]> export(DictDataQueryDTO queryDTO) {
        List<SysDictData> all = this.baseMapper.listPage(queryDTO);
        String fileName = "DictData"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        try {
            return ExcelUtil.generateImportFile(DtoMapper.convertList(all, DictDataListDTO.class), fileName, DictDataListDTO.class);
        }catch (Exception e) {
            throw new BaseKnownException(e.getMessage());
        }
    }

    @Override
    public DictDataDTO save(DictDataDTO saveDTO) {
        SysDictData dictData = DtoMapper.convert(saveDTO, SysDictData.class);
        this.saveOrUpdate(dictData);
        return DtoMapper.convert(dictData, DictDataDTO.class);
    }

    @Override
    public void delete(Integer id) {
        this.removeById(id);
    }

    @Override
    public DictDataDTO find(Integer id){
        SysDictData one = this.getOptById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, DictDataDTO.class);
    }

    


}