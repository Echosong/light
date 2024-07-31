package  cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.ExcelUtil;
import cn.light.common.util.PageUtil;
import cn.light.entity.cache.UserCache;
import cn.light.entity.entity.SysTableColumn;
import cn.light.entity.mapper.TableColumnMapper;
import cn.light.entity.repository.TableColumnRepository;
import cn.light.packet.dto.tableColumn.TableColumnDTO;
import cn.light.packet.dto.tableColumn.TableColumnListDTO;
import cn.light.packet.dto.tableColumn.TableColumnQueryDTO;
import cn.light.server.service.TableColumnService;
import cn.light.server.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 自动生成 存储列设置表 service 实现
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-07-28 22:56:08
 */
@Service
public class TableColumnServiceImpl extends ServiceImpl<TableColumnMapper, SysTableColumn> implements TableColumnService {
    @Resource
    private UserService userService;
    @Resource
    private TableColumnRepository tableColumnRepository;

    @Override
    public Page<TableColumnListDTO> listPage(TableColumnQueryDTO queryDTO){
         Page<SysTableColumn> dataPages  =  PageUtil.getPage(this.baseMapper::listPage, queryDTO);
         return DtoMapper.convertPage(dataPages, TableColumnListDTO.class);
    }

    @Override
    public ResponseEntity<byte[]> export(TableColumnQueryDTO queryDTO) {
        List<SysTableColumn> all = this.baseMapper.listPage(queryDTO);
        String fileName = "TableColumn"+ DateUtil.format(new Date(), "yyyyMMddHHmm")+".xlsx";
        try {
            return ExcelUtil.generateImportFile(DtoMapper.convertList(all, TableColumnListDTO.class), fileName, TableColumnListDTO.class);
        }catch (Exception e) {
            throw new BaseKnownException(e.getMessage());
        }
    }

    @Override
    public TableColumnDTO save(TableColumnDTO saveDTO) {
        SysTableColumn tableColumn = DtoMapper.convert(saveDTO, SysTableColumn.class);
        UserCache userCache = userService.getUserCache();
        tableColumnRepository.findByUserIdAndTableName(userCache.getId(), tableColumn.getTableName())
                .ifPresent(t-> tableColumn.setId(t.getId()));
        this.saveOrUpdate(tableColumn);
        return DtoMapper.convert(tableColumn, TableColumnDTO.class);
    }

    @Override
    public void delete(Integer id) {
        this.removeById(id);
    }

    @Override
    public TableColumnDTO find(Integer id){
        SysTableColumn one = Optional.of(id).map(this::getById)
                             .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, TableColumnDTO.class);
    }

    


}