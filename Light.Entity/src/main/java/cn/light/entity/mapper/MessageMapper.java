package cn.light.entity.mapper;

import cn.light.packet.enums.system.MessageStateEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.light.packet.dto.message.MessageQueryDTO;
import cn.light.entity.entity.SysMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 自动生成 站内消息、短息 记录
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-08-04 22:49:58
 */
@Mapper
public interface MessageMapper extends BaseMapper<SysMessage> {
    List<SysMessage> listPage(MessageQueryDTO messageQueryDTO);

     Long getUnreadCount(Integer receiverId);



}
