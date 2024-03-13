package top.charles7c.continew.admin.front.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import top.charles7c.continew.admin.front.service.WebSocketSendService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static top.charles7c.continew.admin.front.handler.CustomWebSocketHandler.WEB_SOCKET_SESSION_MAP;

/**
 * Created by WeiRan on  2024.03.13 17:23
 */
@Component
@Slf4j
public class WebSocketSendServiceImpl implements WebSocketSendService {

    /**
     * 自定义判断 sessionId
     *
     * @param session 连接对象
     * @return sessionId
     */
    public String getSessionId(WebSocketSession session) {
        return (String) session.getAttributes().get("username");
    }

    /**
     * 发送消息
     *
     * @param sessionId 对象id
     * @param message   消息
     * @throws IOException IO
     */
    public void sendMessage(String sessionId, String message) throws IOException {
        WebSocketSession webSocketSession = WEB_SOCKET_SESSION_MAP.get(sessionId);
        if (webSocketSession == null || !webSocketSession.isOpen()) {
            log.warn("连接对象【{}】已关闭，无法送消息：{}", sessionId, message);
        } else {
            webSocketSession.sendMessage(new TextMessage(message));
            log.info("sendMessage：向{}发送消息：{}", sessionId, message);
        }
    }

    /**
     * 发送消息
     *
     * @param sessionId 对象id
     * @param data      数据
     * @throws IOException IO
     */
    public void sendMessage(String sessionId, Object data) throws IOException {
        sendMessage(sessionId, JSON.toJSONString(data));
    }

    /**
     * 获取所有的连接对象ID
     *
     * @return ids
     */
    public List<String> getSessionIds() {
        Enumeration<String> keys = WEB_SOCKET_SESSION_MAP.keys();
        List<String> ks = new ArrayList<>();
        while (keys.hasMoreElements()) {
            ks.add(keys.nextElement());
        }
        return ks;
    }
}
