/**
 * Created by Administrator on 2017/9/28.
 */
/**
 * @author wuzhe
 * @param url
 * @param msgCallbak
 * @returns {{disconnect: Function, sendMsg: Function}}
 * ps: ������layer.js��socket.min.js
 */
var cfySocket = function(url, msgCallbak) {
	var ws = null; //websocket����

	var connect = function() {
		if(typeof window.WebSocket !== undefined) {
			ws = new WebSocket(url);
			
		} else {
			ws = new SockJS(url);
		}

		//连接成功
		ws.onopen = function(event) {
			console.log("连接成功");
		};

		//接收服务器消息
		ws.onmessage = function(event) {
			msgCallbak(event.data);
		};

		//关闭链接触发
		ws.onclose = function(event) {
			console.log("连接关闭");
		};
	};

	//建立连接
	connect();

	return {
		
		//断开链接
		disconnect: function() {
			if(ws != null) {
				ws.close();
				console.log("退出")
				ws = null;
			}
		},
		
		//发送消息给服务器
		sendMsg: function( message ) {
			if(ws != null) {
				ws.send(message);
			} else {
				comm.layer.alert('��δ����ws���ӣ�');
			}
		}
		
		
	};
};
