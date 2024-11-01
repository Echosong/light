package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.ad.AdQueryDTO;
import cn.light.entity.entity.SysAd;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 广告管理
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-10-05 21:07:18
 */
@Mapper
public interface AdMapper extends BaseMapper<SysAd> {
    List<SysAd> listPage(AdQueryDTO adQueryDTO);
}
