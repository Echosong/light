package cn.light.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.messageTemplate.MessageTemplateQueryDTO;
import cn.light.entity.entity.SysMessageTemplate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 消息模板
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-08-04 22:49:58
 */
@Mapper
public interface MessageTemplateMapper extends BaseMapper<SysMessageTemplate> {
    List<SysMessageTemplate> listPage(MessageTemplateQueryDTO messageTemplateQueryDTO);
}
