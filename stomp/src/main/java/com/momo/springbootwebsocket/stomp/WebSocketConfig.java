package com.momo.springbootwebsocket.stomp;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 配置STOMP 的服务端点和请求订阅前缀
 * 
 * @author: MQG
 * @date: 2018/11/9
 */
@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        // 增加一个聊天服务端点
        stompEndpointRegistry.addEndpoint("/socket").withSockJS();
        // 增加一个用户服务端点
        stompEndpointRegistry.addEndpoint("/wsuser").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry) {
        // 客户端订阅路径前缀
        messageBrokerRegistry.enableSimpleBroker("/sub", "/queue");
        // 服务端点请求前缀
        messageBrokerRegistry.setApplicationDestinationPrefixes("/request");
    }
    
}
