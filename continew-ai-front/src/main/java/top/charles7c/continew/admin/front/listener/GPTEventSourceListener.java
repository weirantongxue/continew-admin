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
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import top.charles7c.continew.admin.front.constant.TimerConstant;
import top.charles7c.continew.admin.front.enums.EventNameType;
import top.charles7c.continew.admin.front.model.entity.ChatMessageDO;
import top.charles7c.continew.admin.front.service.ChatMessageService;

import java.util.Objects;

/**
 * Created by WeiRan on 2023.09.15 12:08
 */
@Slf4j
public class GPTEventSourceListener extends EventSourceListener {

    private final SseEmitter sseEmitter;
    private final String messageId;
    private final ChatMessageService messageService;

    private final ChatMessageDO message;

    private final TimeInterval timer;

    private String last = "";

    public GPTEventSourceListener(SseEmitter sseEmitter,
                                  String messageId,
                                  ChatMessageService messageService,
                                  ChatMessageDO message,
                                  TimeInterval timer) {
        this.sseEmitter = sseEmitter;
        this.messageId = messageId;
        this.messageService = messageService;
        this.message = message;
        this.timer = timer;
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
        System.out.println("收到消息:" + data);
        if (data.equals("[DONE]")) {
            // messageService.insertMessage(MessageUtils.setMessageDO(message,last, timer.intervalMs(TimerConstant.RESPONSE_TIME), timer.intervalMs(TimerConstant.CHAT_RESPONSE_TIME)));
            sseEmitter.send(SseEmitter.event()
                .id(messageId)
                .name(EventNameType.FINISH.getCode())
                .data(EventNameType.DONE.getCode())
                .reconnectTime(3000));
            sseEmitter.complete();
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        ChatCompletionResponse completionResponse = mapper.readValue(data, ChatCompletionResponse.class); // 读取Json
        String content = completionResponse.getChoices().get(0).getDelta().getContent();
        if (content != null) {
            if (null != completionResponse.getUsage()) {
                message.setTotalTokens((int)completionResponse.getUsage().getTotalTokens());
                message.setInputTokens((int)completionResponse.getUsage().getPromptTokens());
                message.setOutputTokens((int)completionResponse.getUsage().getCompletionTokens());
            }

            last = last + content;
            sseEmitter.send(SseEmitter.event()
                .id(messageId)
                .name(EventNameType.ADD.getCode())
                .data(completionResponse.getChoices().get(0).getDelta())
                .reconnectTime(3000));
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
        sseEmitter.complete();
    }

    @SneakyThrows
    @Override
    public void onFailure(EventSource eventSource, Throwable t, Response response) {
        if (Objects.isNull(response)) {
            return;
        }
        ResponseBody body = response.body();
        if (Objects.nonNull(body)) {
            log.error("OpenAI  sse连接异常data：{}，异常：{}", body.string(), t);
        } else {
            log.error("OpenAI  sse连接异常data：{}，异常：{}", response, t);
        }
        eventSource.cancel();
        sseEmitter.complete();
    }

}
