package top.charles7c.continew.admin.front.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import top.charles7c.continew.admin.front.handler.CustomWebSocketHandler;

/**
 * Created by WeiRan on  2024.03.13 16:45
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Resource
    private CustomWebsocketInterceptor customWebsocketInterceptor;

    @Resource
    private CustomWebSocketHandler customWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                // 设置处理器处理/custom/**
                .addHandler(customWebSocketHandler, "/ws")
                // 允许跨越
                .setAllowedOrigins("*")
                // 设置监听器
                .addInterceptors(customWebsocketInterceptor);
    }

}

