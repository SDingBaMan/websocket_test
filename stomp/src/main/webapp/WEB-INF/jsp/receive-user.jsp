<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>WebSocket测试</title>
    </head>
    <body>
        <h1><span id="receive">等待接收消息</span></h1>
        
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="https://cdn.bootcss.com/sockjs-client/1.3.0/sockjs.min.js"></script>
        <script src="https://cdn.bootcss.com/stomp.js/2.3.3/stomp.min.js"></script>
        <script type="text/javascript">
            var noticeSocket = function() {
                var s = new SockJS('/socket');
                var stompClient = Stomp.over(s);
                stompClient.connect({}, function() {
                   console.log('notice socket connected!');
                   // 订阅消息地址
                    stompClient.subscribe('/sub/chat', function (data) {
                       $('#receive').html(data.body); 
                    });
                });
            };
            noticeSocket();
        </script>
    </body>
</html>