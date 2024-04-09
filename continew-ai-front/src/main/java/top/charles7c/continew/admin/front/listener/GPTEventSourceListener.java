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

package top.charles7c.continew.admin.front.listener;

import cn.hutool.core.date.TimeInterval;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import top.charles7c.continew.admin.common.constant.TimerConstant;
import top.charles7c.continew.admin.common.enums.EventNameType;
import top.charles7c.continew.admin.front.model.ChatMessageUtils;
import top.charles7c.continew.admin.front.model.entity.ChatMessageDO;
import top.charles7c.continew.admin.front.service.ChatMessageService;
import top.charles7c.continew.admin.front.service.WebSocketSendService;

import java.util.Objects;

/**
 * Created by WeiRan on 2023.09.15 12:08
 */
@Slf4j
@Data
public class GPTEventSourceListener extends EventSourceListener {

    private final WebSocketSendService webSocketSendService;
    private final String messageId;
    private final ChatMessageService chatMessageService;

    private final String sessionId;

    private final ChatMessageDO message;

    private final TimeInterval timer;

    private final Long deptId;

    private String last = "";

    public GPTEventSourceListener(WebSocketSendService webSocketSendService,
                                  String sessionId,
                                  String messageId,
                                  ChatMessageService chatMessageService,
                                  ChatMessageDO message,
                                  TimeInterval timer,
                                  Long deptId) {
        this.webSocketSendService = webSocketSendService;
        this.sessionId = sessionId;
        this.messageId = messageId;
        this.chatMessageService = chatMessageService;
        this.message = message;
        this.timer = timer;
        this.deptId = deptId;
    }

    /**
     * 建立连接
     *
     * @param eventSource
     * @param response
     */

    @Override
    public void onOpen(EventSource eventSource, Response response) {
        timer.start(TimerConstant.CHAT_RESPONSE_TIME);
        log.info("建立sse连接...");
    }

    /**
     * 接收事件
     *
     * @param eventSource
     * @param id
     * @param type
     * @param data
     */
    @SneakyThrows
    @Override
    public void onEvent(EventSource eventSource, String id, String type, String data) {
        log.info("收到消息:" + data);
        if (data.equals("[DONE]")) {
            webSocketSendService.sendMessage(sessionId, ChatMessageUtils
                .chatModelMsg(messageId, sessionId, "DONE", EventNameType.DONE.getCode()));
            chatMessageService.insertMessage(ChatMessageUtils.setMessageDO(message, last, timer
                .intervalMs(TimerConstant.RESPONSE_TIME), timer.intervalMs(TimerConstant.CHAT_RESPONSE_TIME)),deptId);
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        ChatCompletionResponse completionResponse = mapper.readValue(data, ChatCompletionResponse.class); // 读取Json
        String content = completionResponse.getChoices().get(0).getDelta().getContent();
        if (content != null) {
            if (null != completionResponse.getUsage()) {
                message.setTotalTokens(completionResponse.getUsage().getTotalTokens());
                message.setPromptTokens(completionResponse.getUsage().getPromptTokens());
                message.setCompletionTokens(completionResponse.getUsage().getCompletionTokens());
                message.setTaskId(completionResponse.getId());
            }

            last = last + content;
            webSocketSendService.sendMessage(sessionId, ChatMessageUtils
                .chatModelMsg(messageId, sessionId, content, EventNameType.ADD.getCode()));
        }
    }

    /**
     * 关闭连接
     *
     * @param eventSource
     */
    @Override
    public void onClosed(EventSource eventSource) {
        log.info("sse连接关闭messageId:{}", messageId);
    }

    @SneakyThrows
    @Override
    public void onFailure(EventSource eventSource, Throwable t, Response response) {
        if (Objects.isNull(response)) {
            return;
        }
        ResponseBody body = response.body();
        if (Objects.nonNull(body)) {
            log.error("sse连接异常data：{}，异常：{}", body.string(), t);
        } else {
            log.error("sse连接异常data：{}，异常：{}", response, t);
        }
        webSocketSendService.sendMessage(sessionId, ChatMessageUtils
            .chatModelMsg(messageId, sessionId, "模型服务异常请联系管理员", EventNameType.ERROR.getCode()));
        webSocketSendService.close(sessionId, "sse连接异常");
        eventSource.cancel();
    }
}
