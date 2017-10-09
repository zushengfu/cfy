package com.javen.function;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 消息处理类
 * @author ts
 *
 */
public class WebSocketPushHandler implements WebSocketHandler{
	
	private static final List<WebSocketSession> users = new ArrayList<>();
//	private static final List<WebSocketSession> FileInfoSend =new ArrayList<>();
//	private static final List<WebSocketSession> fileRecordsSend=new ArrayList<>();
	//用户进入系统监听
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("成功进入了系统。。。");
		users.add(session);
	}

	//建立好链接之后的处理函数
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
	    //将消息进行转化，因为是消息是json数据，可能里面包含了发送给某个人的信息，所以需要用json相关的工具类处理之后再封装成TextMessage，
	   // 我这儿并没有做处理，消息的封装格式一般有{from:xxxx,to:xxxxx,msg:xxxxx}，来自哪里，发送给谁，什么消息等等	
		System.out.println(message.getPayload());
	    TextMessage msg = (TextMessage)message.getPayload();
	    //给所有用户群发消息
	    sendMessagesToUsers(msg);
	    //给指定用户群发消息
//	    sendMessageToUser("3",msg);
		
	}
        
        //后台错误信息处理方法
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.println("操蛋，这边出错了？");
	}

	//用户退出后的处理，不如退出之后，要将用户信息从websocket的session中remove掉，这样用户就处于离线状态了，也不会占用系统资源
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		if(session.isOpen()){
			session.close();
		}
		users.remove(session);
		System.out.println("安全退出了系统");
		
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
	
	/**
	 * 给所有的用户发送消息
	 */
	public void sendMessagesToUsers(TextMessage message){
		for(WebSocketSession user : users){
			try {
			    //isOpen()在线就发送
				if(user.isOpen()){
					user.sendMessage(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 发送消息给指定的用户
	 */
	public void sendMessageToUser(String userId,TextMessage message){
		for(WebSocketSession user : users){
//			if(user.getAttributes().get("0").equals(userId)){
//				try {
//				    //isOpen()在线就发送
//					if(user.isOpen()){
//						user.sendMessage(message);
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
			if(user.getHandshakeAttributes().get("3").equals(userId)){
				try {
				    //isOpen()在线就发送
					if(user.isOpen()){
						user.sendMessage(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}