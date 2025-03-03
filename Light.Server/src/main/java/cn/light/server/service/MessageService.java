package  cn.light.server.service;

import cn.light.entity.entity.SysMessage;
import cn.light.packet.dto.message.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * 自动生成 站内消息、短息 记录 service 接口
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-08-04 22:49:58
 */
public interface MessageService extends IService<SysMessage> {

    Page<MessageListDTO> listPage(MessageQueryDTO queryDTO);

    ResponseEntity<byte[]> export(MessageQueryDTO queryDTO);

    MessageDTO save(MessageDTO messageDTO);

    MessageDTO find(Integer id);


    Long unreadCount();

    void read(Integer id);
}