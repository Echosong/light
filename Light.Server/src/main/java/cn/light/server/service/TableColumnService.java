package  cn.light.server.service;

import cn.light.entity.entity.SysTableColumn;
import cn.light.packet.dto.tableColumn.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * 自动生成 存储列设置表 service 接口
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-07-28 22:56:08
 */
public interface TableColumnService extends IService<SysTableColumn> {

    Page<TableColumnListDTO> listPage(TableColumnQueryDTO queryDTO);

    ResponseEntity<byte[]> export(TableColumnQueryDTO queryDTO);

    TableColumnDTO save(TableColumnDTO tableColumnDTO);

    String getColumns(String tableName);

    void delete(String tableName);
}