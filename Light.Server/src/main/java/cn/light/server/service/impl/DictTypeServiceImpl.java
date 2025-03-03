package  cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.ExcelUtil;
import cn.light.common.util.PageUtil;
import cn.light.entity.entity.SysDictType;
import cn.light.entity.mapper.DictTypeMapper;
import cn.light.packet.dto.dictType.DictTypeDTO;
import cn.light.packet.dto.dictType.DictTypeListDTO;
import cn.light.packet.dto.dictType.DictTypeQueryDTO;
import cn.light.server.service.DictTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 自动生成 字典类型 service 实现
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-07-24 22:17:47
 */
@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, SysDictType> implements DictTypeService {

    @Override
    public Page<DictTypeListDTO> listPage(DictTypeQueryDTO queryDTO){
         Page<SysDictType> dataPages  =  PageUtil.getPage(this.baseMapper::listPage, queryDTO);
         return DtoMapper.convertPage(dataPages, DictTypeListDTO.class);
    }

    @Override
    public ResponseEntity<byte[]> export(DictTypeQueryDTO queryDTO) {
        List<SysDictType> all = this.baseMapper.listPage(queryDTO);
        String fileName = "DictType"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        try {
            return ExcelUtil.generateImportFile(DtoMapper.convertList(all, DictTypeListDTO.class), fileName, DictTypeListDTO.class);
        }catch (Exception e) {
            throw new BaseKnownException(e.getMessage());
        }
    }

    @Override
    public DictTypeDTO save(DictTypeDTO saveDTO) {
        SysDictType dictType = DtoMapper.convert(saveDTO, SysDictType.class);
        this.save(dictType);
        return DtoMapper.convert(dictType, DictTypeDTO.class);
    }

    @Override
    public void delete(Integer id) {
        this.removeById(id);
    }

    @Override
    public DictTypeDTO find(Integer id){
        SysDictType one = this.getOptById(id)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, DictTypeDTO.class);
    }

    


}