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

package top.continew.admin.ai.listener;

import cn.hutool.core.date.TimeInterval;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import org.springframework.http.MediaType;
import top.continew.admin.ai.enums.EventNameType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import top.continew.admin.ai.constant.TimerConstant;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class GlmEventSourceListener extends EventSourceListener {

    private final SseEmitter sseEmitter;
    private final String messageId;
    private final TimeInterval timer;
    private String last = "";

    public GlmEventSourceListener(SseEmitter sseEmitter, String messageId, TimeInterval timer) {
        this.sseEmitter = sseEmitter;
        this.messageId = messageId;
        this.timer = timer;
    }

    @Override
    public void onOpen(EventSource eventSource, Response response) {
        timer.start(TimerConstant.MODEL_REQUEST_TIME);
        log.info("建立sse连接...");
    }

    @SneakyThrows
    @Override
    public void onEvent(EventSource eventSource, String id, String type, String data) {
        log.info("收到sse事件data：{}", data);
        if (data.equals("[DONE]")) {
            sseEmitter.send(SseEmitter.event()
                .id(messageId)
                .name(EventNameType.DONE.getCode())
                .data(EventNameType.DONE.getCode())
                .reconnectTime(3000));
            sseEmitter.complete();
            return;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            ChatCompletionResponse completionResponse = mapper.readValue(data, ChatCompletionResponse.class); // 读取Json
            String content = completionResponse.getChoices().get(0).getDelta().getContent();

            if (content != null) {
                last += content;
                log.info("last：{}", last);
                sseEmitter.send(SseEmitter.event()
                    .id(messageId)
                    .name(EventNameType.ADD.getCode())
                    .data(content, MediaType.APPLICATION_JSON)
                    .reconnectTime(3000));
                log.info("SSE 消息发送成功: {}", content);
            }
        } catch (Exception e) {
            log.error("处理 SSE 事件失败：{}", e.getMessage(), e);
            sseEmitter.completeWithError(e);
        }
    }

    @Override
    public void onClosed(EventSource eventSource) {
        log.info("sse连接关闭messageId:{}", messageId);
        sseEmitter.complete();
    }

    @SneakyThrows
    @Override
    public void onFailure(EventSource eventSource, Throwable t, Response response) {
        Optional.ofNullable(response).ifPresentOrElse(res -> {
            try (ResponseBody body = res.body()) {
                if (Objects.nonNull(body)) {
                    log.error("OpenAI sse连接异常data：{}，异常：{}", body.string(), t);
                } else {
                    log.error("OpenAI sse连接异常data：{}，异常：{}", res, t);
                }
            } catch (IOException e) {
                log.error("Error reading response body: {}", e.getMessage(), e);
            }
        }, () -> log.error("OpenAI sse连接异常data：异常：{}", t));

        eventSource.cancel();
        sseEmitter.complete();
    }
}
