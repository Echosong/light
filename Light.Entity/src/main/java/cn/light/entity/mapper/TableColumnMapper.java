package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.tableColumn.TableColumnQueryDTO;
import cn.light.entity.entity.SysTableColumn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * 自动生成 存储列设置表
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-07-28 22:43:24
 */
@Mapper
public interface TableColumnMapper extends BaseMapper<SysTableColumn> {
    List<SysTableColumn> listPage(TableColumnQueryDTO tableColumnQueryDTO);
}
