package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.file.FileQueryDTO;
import cn.light.entity.entity.KdFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 文件管理
 * @author : 二胡子
 * @version :1.0.0
 */
@Mapper
public interface FileMapper extends BaseMapper<KdFile> {
    List<KdFile> listPage(FileQueryDTO fileQueryDTO);
}
