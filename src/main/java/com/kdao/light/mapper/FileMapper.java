package com.kdao.light.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kdao.light.common.dto.file.FileQueryDTO;
import com.kdao.light.entity.KdFile;
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
