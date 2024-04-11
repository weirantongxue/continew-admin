/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.charles7c.continew.admin.front.service.impl;

import com.alibaba.fastjson.JSONObject;
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
 * Created by WeiRan on 2024.03.13 17:23
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
        return (String)session.getAttributes().get("userId");
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
        sendMessage(sessionId, JSONObject.toJSONString(data));
    }

    /**
     * 主动关闭连接
     * 
     * @param sessionId
     * @param msg
     * @throws IOException
     */
    public void close(String sessionId, String msg) throws IOException {
        WebSocketSession webSocketSession = WEB_SOCKET_SESSION_MAP.get(sessionId);
        if (webSocketSession == null || !webSocketSession.isOpen()) {
            log.warn("连接对象【{}】已关闭：{}", sessionId, msg);
        } else {
            webSocketSession.close();
            WEB_SOCKET_SESSION_MAP.remove(sessionId);
            log.info("服务端主动关闭连接：用户{}错误信息：{}", sessionId, msg);
        }
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
