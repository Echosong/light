package com.kdao.light.controller;

import jakarta.annotation.Resource;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author websocket
 */
@Controller
public class WebSocketController {

    @Resource
    private  SimpMessageSendingOperations messageSendingOperations;


    @MessageMapping("/sendMessage")
    @SendTo("/app/user/{user}")
    public TextMessage sendMessage(String message, WebSocketSession session) {
        return new TextMessage(message + " " + session.getAttributes().get("user").toString());
    }

    @MessageMapping("/join")
    public void join(String user, WebSocketSession session) {
        messageSendingOperations.convertAndSend("/app/user/" + user, user + " has joined");
        session.getAttributes().put("user", user);
    }

    @MessageMapping("/leave")
    public void leave(WebSocketSession session) {
        Object user = session.getAttributes().remove("user");
        messageSendingOperations.convertAndSend("/app/user/" + user, user + " has left");
    }


}
