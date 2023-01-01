package com.kdao.light.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * <p>Title: LightController</p >
 * <p>Description: WebSocketConfig</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2022-10-29 15:24 </p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Configuration
public class WebSocketConfig {
    /**
     * ServerEndpointExporter注入
     * 该Bean会自动注册使用@ServerEndpoint注解申明的WebSocket endpoint
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
