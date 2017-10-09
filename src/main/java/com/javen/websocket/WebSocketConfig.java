package com.javen.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
  
//创建websocket处理类
@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    	System.out.println("that means i can ento this method");
        registry.addHandler(WebSocketPushHandler(),"/webSocketServer.do").addInterceptors(new MyWebSocketInterceptor());
        registry.addHandler(WebSocketPushHandler(), "/sockjs/webSocketServer.do").addInterceptors(new MyWebSocketInterceptor())
                .withSockJS();
    }
    @Bean	
    public WebSocketHandler WebSocketPushHandler(){
    	return new WebSocketPushHandler();
    }
}