package com.kdao.light.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Title: 根据球场id 对应session作为唯一一个用户</p >
 * <p>Description: WebSocketService</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2022-10-29 15:25 </p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Component
@ServerEndpoint("/websocket/{courtId}")
public class WebSocketService {
    private final Logger logger = LoggerFactory.getLogger(WebSocketService.class);

    /**
     * 保存连接信息
     */
    private static final Map<String, Session> CLIENTS = new ConcurrentHashMap<>();
    private static final Map<String, AtomicInteger> TERMINAL_IDS = new HashMap<>();

    @OnOpen
    public void onOpen(@PathParam("courtId") String courtId, Session session) throws Exception {
        logger.info(session.getRequestURI().getPath() + "，打开连接开始：" + session.getId());

        //当前连接已存在，关闭
        if (CLIENTS.containsKey(courtId)) {
            onClose(CLIENTS.get(courtId));
        }

        TERMINAL_IDS.put(courtId, new AtomicInteger(0));
        CLIENTS.put(courtId, session);

        logger.info(session.getRequestURI().getPath() + "，打开连接完成：" + session.getId());
    }

    @OnClose
    public void onClose(@PathParam("terminalId") String terminalId, Session session) throws Exception {
        logger.info(session.getRequestURI().getPath() + "，关闭连接开始：" + session.getId());

        CLIENTS.remove(terminalId);
        TERMINAL_IDS.remove(terminalId);
        logger.info(session.getRequestURI().getPath() + "，关闭连接完成：" + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("前台发送消息：" + message);
        if ("心跳".equals(message)) {
            //重置当前终端心跳次数
            TERMINAL_IDS.get(message).set(0);
            return;
        }
        sendMessage("收到消息：" + message, session);
    }
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error(error.toString());
    }
    public void onClose(Session session) {
        //判断当前连接是否在线
        //        if (!session.isOpen()) {
        //            return;
        //        }
        try {
            session.close();
        } catch (IOException e) {
            logger.error("金斗云关闭连接异常：" + e);
        }
    }

    public void heartbeat() {
        //检查所有终端心跳次数
        for (String key : TERMINAL_IDS.keySet()) {
            //心跳3次及以上的主动断开
            if ((TERMINAL_IDS.get(key).intValue() >= 3)) {
                logger.info("心跳超时，关闭连接：" + key);
                onClose(CLIENTS.get(key));
            }
        }

        for (String key : CLIENTS.keySet()) {
            //记录当前终端心跳次数
            TERMINAL_IDS.get(key).incrementAndGet();
            sendMessage("心跳", CLIENTS.get(key));
        }
    }

    public void sendMessage(String message, Session session) {
        try {
            session.getAsyncRemote().sendText(message);

            logger.info("推送成功：" + message);
        } catch (Exception e) {
            logger.error("推送异常：" + e);
        }
    }

    public boolean sendMessage(String courtId, String message) {
        try {
            Session session = CLIENTS.get(courtId);
            session.getAsyncRemote().sendText(message);
            logger.info("推送成功：" + message);
            return true;
        } catch (Exception e) {
            logger.error("推送异常：" + e);
            return false;
        }
    }
}
