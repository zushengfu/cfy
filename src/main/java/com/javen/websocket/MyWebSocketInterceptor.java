package com.javen.websocket;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.javen.model.User;

/**
 * 此类用来获取登录用户信息并交由websocket管理
 * @author ts
 *
 */
public class MyWebSocketInterceptor implements HandshakeInterceptor{
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		
//		将ServerHttpRequest转换成request请求相关的类，用来获取request域中的用户信息	
		if (request instanceof ServletServerHttpRequest) {
			System.out.println("載入socket鏈接");
			ServletServerHttpRequest servletRequest  = (ServletServerHttpRequest) request;
			HttpServletRequest httpRequest = servletRequest.getServletRequest();
//			Constants.CURRENT_USER这个是我定义的常量，是request域的key，通过key就可以获取到用户信息了
			User user = (User)httpRequest.getSession().getAttribute("user");
//			Constants.CURRENT_WEBSOCKET_USER也是常量，用来存储WebsocketSession的key值
			attributes.put(user.getId()+"",user.getId());
		}
		return true ;
	}
	
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {		
		System.out.println("why like this!");
	}
}