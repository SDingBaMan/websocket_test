var websocket = null;

// 判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
    // 创建WebSocket 对象，连接服务器端点
    websocket = new WebSocket("ws://localhost:8080/ws");
} else {
        alert('Not support websocket');
}

// 连接发生错误的回调方法
websocket.onerror = function() {
    appendMessage ("error");
}

// 连接成功建立的回调方法
websocket.onopen = function(event) {
    appendMessage ("open");
}

// 接收到消息的回调方法
websocket.onmessage = function (event) {
    appendMessage(event.data);
}

websocket.onclose = function() {
    appendMessage("close");
}

websocket.onbeforeupload = function() {
    websocket.close();
}

function appendMessage(message) {
    var context = $('#context').html() + '<br>' + message;
    $('#context').html(context);
}

function closeWebSocket() {
    websocket.close();
}

function sendMessage() {
    var message = $('#message').val();
    websocket.send(message);
}
