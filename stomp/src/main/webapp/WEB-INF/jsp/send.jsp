<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>WebSocket测试</title>
    </head>
    <body>
        <div>
            <div>
                <button id="connect" onclick="connect();">连接</button>
                <button id="disconnect" disabled="disabled" onclick="disconnect();">断开连接</button>
            </div>
            <div id="conversationDiv">
                <p>
                    <label>发送的内容</label>
                </p>
                <p>
                    <textarea id="message" rows="5"></textarea>
                </p>
                <button id="sendMsg" onclick="sendMsg();">Send</button>
                <p id="response"></p>
            </div>
        </div>
        
        
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="https://cdn.bootcss.com/sockjs-client/1.3.0/sockjs.min.js"></script>
        <script src="https://cdn.bootcss.com/stomp.js/2.3.3/stomp.min.js"></script>
        <script type="text/javascript">
            var stompClient = null;
            
            // 设置连接
            function setConnected(conncted) {
                $('#connect').attr({
                   'disabled': conncted 
                });
                $('#disconnect').attr({
                    'disabled': !conncted
                });
                if (conncted) {
                    $('#conversationDiv').show();
                } else {
                    $('#conversationDiv').hide();
                }
                $('#response').html('');
            }
            
            // 开启socket连接
            function connect() {
                // 定义请求服务器的端点
                var socket = new SockJS('/socket');
                // stomp客户端
                stompClient = Stomp.over(socket);
                // 连接服务器端点
                stompClient.connect({}, function(frame) {
                    setConnected(true);
                });
            }
            
            // 断开socket连接
            function disconnect() {
                if (stompClient != null) {
                    stompClient.disconnect();
                }
                
                setConnected(false);
                console.log("Disconnected");
            }
            
            // 向request/send服务端发送消息
            function sendMsg() {
                var value = $('#message').val();
                // 发送消息到/request/send，其中/request是服务器定义的前缀，/send是@MessageMapping所配置的路径
                stompClient.send('/request/send', {}, value);
            }
        </script>
    </body>
</html>