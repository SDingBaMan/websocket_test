package com.momo.springbootwebsocket.stomp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * @author: MQG
 * @date: 2018/11/9
 */
@Controller
@RequestMapping("/static/websocket")
public class WebSocketController {
    
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    // 定义消息请求路径
    @MessageMapping("/send")
    // 定义结果发送到特定路径
    @SendTo("/sub/chat")
    public String sendMsg(String value) {
        return value;
    }
    
    // 将消息发送给特定用户
    @MessageMapping("/sendUser")
    public void sendToUser(Principal principal, String body) {
        String srcUser = principal.getName();
        // 解析用户和消息
        String[] args = body.split(",");
        String desUser = args[0];
        String message = "【" + srcUser + "】给你发来消息：" + args[1];
        // 发送到用户和监听地址
        simpMessagingTemplate.convertAndSendToUser(desUser, "/queue/customer", message);
    }

}
