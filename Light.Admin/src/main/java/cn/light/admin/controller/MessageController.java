package cn.light.admin.controller;
import cn.light.common.annotation.*;
import cn.light.common.consts.Consts;
import cn.light.packet.dto.message.MessageDTO;
import cn.light.packet.dto.message.MessageListDTO;
import cn.light.packet.dto.message.MessageQueryDTO;
import cn.light.server.service.MessageService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.*;

/**
 *  站内消息、短息 记录 控制器
 *  email:zq_songfeigang@163.com
 *
 * @author : 二胡子
 * @date : 2024-08-04 22:49:58
 */
@Tag(name = "站内消息、短息 记录 控制器")
@RestController
@RequestMapping("/message")
public class MessageController extends BaseController{

    @Resource
    private MessageService messageService;

    @Operation(summary = "分页查询站内消息、短息 记录")
    @PutMapping("/listPage")
    public Page<MessageListDTO> listPage(@RequestBody @Valid MessageQueryDTO queryDTO){
        return messageService.listPage(queryDTO);
    }

    @PutMapping("/export")
    @ApiResultIgnore
    @Log("导出站内消息、短息 记录")
    public ResponseEntity<byte[]> export(@RequestBody @Valid MessageQueryDTO queryDTO) {
        return messageService.export(queryDTO);
    }

    @Operation(summary = "新增活更新站内消息、短息 记录")
    @PostMapping("/save")
    @Log("新增|修改站内消息、短息 记录")
    public void save(@RequestBody @Valid MessageDTO messageDTO){
         messageService.save(messageDTO);
    }


    @Operation(summary = "查询单个明细")
    @GetMapping("/find/{id}")
    public MessageDTO find(@PathVariable Integer id){
        return messageService.find(id);
    }

    @GetMapping("/unreadCount")
    @Operation(summary = "获取未读消息数量")
    public Long unreadCount(){
        return messageService.unreadCount();
    }

    @Operation(summary = "标记已读")
    @GetMapping("/read/{id}")
    public void read(@PathVariable Integer id){
        messageService.read(id);
    }

    

}