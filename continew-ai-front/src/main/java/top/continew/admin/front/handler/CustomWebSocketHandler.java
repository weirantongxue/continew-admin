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

package top.continew.admin.front.handler;

/**
 * Created by WeiRan on 2024.03.13 16:41
 */

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import top.continew.admin.common.util.WsUtils;
import top.continew.admin.front.model.validate.ChatMessageRequestValidate;
import top.continew.admin.front.service.ChatGlmService;
import top.continew.admin.front.service.WebSocketSendService;

import java.io.IOException;

/**
 * @author zhong
 *         webscoket 处理器
 */
@Component
@Slf4j
public class CustomWebSocketHandler extends TextWebSocketHandler {
    @Resource
    private WebSocketSendService webSocketSendService;
    @Resource
    private ChatGlmService chatGlmService;

    /**
     * 当前websocket连接集合
     */
    //public static final ConcurrentHashMap<String, WebSocketSession> WEB_SOCKET_SESSION_MAP = new ConcurrentHashMap<>();

    /**
     * 收到客户端消息时触发的回调
     *
     * @param session 连接对象
     * @param message 消息体
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        log.info("接受到会话【{}】的消息：{}", session.getId(), message.getPayload());
        String jsonPayload = message.getPayload();
        // Check for empty message
        if (StrUtil.isBlank(jsonPayload)) {
            log.error("接收到空消息");
            webSocketSendService.close(session.getId(), "接收到空消息");
            return;
        }
        try {
            ChatMessageRequestValidate chatMessageRequestValidate = JSONObject
                .parseObject(jsonPayload, ChatMessageRequestValidate.class);
            chatGlmService.aiApi(chatMessageRequestValidate, webSocketSendService.getSessionId(session));
        } catch (Exception e) {
            log.error("WebSocket消息解析失败：{}", e.getMessage(), e);
            webSocketSendService.close(session.getId(), "消息解析失败：" + e.getMessage());
        }
    }

    /**
     * 建立连接后触发的回调
     *
     * @param session 连接对象
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String sessionId = webSocketSendService.getSessionId(session);
        // 如果存在则断开连接
        if (WsUtils.contains(sessionId)) {
            WsUtils.close(sessionId);
        }
        // 将新连接添加
        WsUtils.add(sessionId, session);
        log.info("与用户【{}】建立了连接", sessionId);
        log.info("attributes:{}", session.getAttributes());

    }

    /**
     * 断开连接后触发的回调
     *
     * @param session 连接对象
     * @param status  状态
     * @throws Exception 异常
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("用户【{}】断开连接,status:{}", webSocketSendService.getSessionId(session), status.getCode());
        // 关闭连接
        session.close(CloseStatus.SERVER_ERROR);
        // 删除对象
        WsUtils.remove(webSocketSendService.getSessionId(session));
    }

    /**
     * 传输消息出错时触发的回调
     *
     * @param session   连接对象
     * @param exception 异常
     * @throws Exception 异常
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("用户【{}】发生错误,exception:{}", session.getId(), exception.getMessage());
        // 如果发送异常，则断开连接
        if (session.isOpen()) {
            session.close();
        }
        WsUtils.remove(webSocketSendService.getSessionId(session));
    }
}
