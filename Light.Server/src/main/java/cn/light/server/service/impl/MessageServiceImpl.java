package cn.light.server.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.light.common.exception.BaseKnownException;
import cn.light.common.util.DtoMapper;
import cn.light.common.util.ExcelUtil;
import cn.light.common.util.PageUtil;

import cn.light.packet.enums.system.MessageStateEnum;
import cn.light.server.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.light.entity.entity.SysMessage;
import cn.light.entity.mapper.MessageMapper;
import cn.light.server.service.MessageService;
import cn.light.packet.dto.message.*;

import org.springframework.http.ResponseEntity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.data.domain.Page;

import java.util.*;

/**
 * 自动生成 站内消息、短息 记录 service 实现
 * email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @version : 1.0
 * @date : 2024-08-04 22:49:58
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, SysMessage> implements MessageService {

    @Resource
    private UserService userService;

    @Override
    public Page<MessageListDTO> listPage(MessageQueryDTO queryDTO) {
        Page<SysMessage> dataPages = PageUtil.getPage(this.baseMapper::listPage, queryDTO);
        return DtoMapper.convertPage(dataPages, MessageListDTO.class);
    }

    @Override
    public ResponseEntity<byte[]> export(MessageQueryDTO queryDTO) {
        List<SysMessage> all = this.baseMapper.listPage(queryDTO);
        String fileName = "Message" + DateUtil.format(new Date(), "yyyyMMddHHmm") + ".xlsx";
        try {
            return ExcelUtil.generateImportFile(DtoMapper.convertList(all, MessageListDTO.class), fileName, MessageListDTO.class);
        } catch (Exception e) {
            throw new BaseKnownException(e.getMessage());
        }
    }

    @Override
    public MessageDTO save(MessageDTO saveDTO) {
        SysMessage message = DtoMapper.convert(saveDTO, SysMessage.class);
        this.saveOrUpdate(message);
        return DtoMapper.convert(message, MessageDTO.class);
    }

    @Override
    public Long unreadCount() {
        return this.baseMapper.getUnreadCount(userService.getUserCache().getId());
    }

    @Override
    public void read(Integer id) {
        SysMessage message = new SysMessage();
        message.setId(id);
        message.setState(MessageStateEnum.READ.getCode());
        this.updateById(message);
    }

    @Override
    public MessageDTO find(Integer id) {
        SysMessage one = Optional.of(id).map(this::getById)
                .orElseThrow(() -> new BaseKnownException(500, "该数据不存在"));
        return DtoMapper.convert(one, MessageDTO.class);
    }


}