package com.momo.springbootwebsocket.websocket.service;

import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

// 表示让Spring创建WebSocket的服务端点，请求地址是 /ws
@ServerEndpoint("/ws")
@Service
public class WebSocketService {
    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<WebSocketService> webSocketServiceSet = new CopyOnWriteArraySet<>();

    private Session session;

    public Session getSession() {
        return session;
    }

    // 标注客户端打开WebSocket服务端点调用方法
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketServiceSet.add(this);
        addOnlineCount();
        System.out.println("有新连接加入，当前在线人数：" + getOnlineCount());
        try {
            sendMessage("有新连接加入了！");
        } catch (Exception e) {
            System.out.println("IO异常");
        }
    }

    // 标注客户端关闭WebSocket服务端点调用方法
    @OnClose
    public void onClose() {
        webSocketServiceSet.remove(this);
        subOnlineCount();
        System.out.println("有连接关闭，当前在线人数：" + getOnlineCount());
    }

    // 标注客户端发送消息，WebSocket服务端点调用方法
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息：" + message);

        for (WebSocketService webSocketService : webSocketServiceSet) {

            try {
                // 获取当前用户名称
//                String userName = webSocketService.getSession().getUserPrincipal().getName();
                webSocketService.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 标注客户端请求WebSocket服务端点发生异常调用方法
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        WebSocketService.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        WebSocketService.onlineCount--;
    }
}
