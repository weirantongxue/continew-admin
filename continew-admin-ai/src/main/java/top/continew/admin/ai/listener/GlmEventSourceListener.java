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
import org.jetbrains.annotations.NotNull;
import top.continew.admin.ai.enums.EventNameType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import top.continew.admin.ai.constant.TimerConstant;
import top.continew.admin.ai.utils.SseSendUtils;

@Slf4j
public class GlmEventSourceListener extends EventSourceListener {

    private final SseEmitter sseEmitter;
    private final String messageId;
    private final TimeInterval timer;
    private final StringBuilder last = new StringBuilder();

    /**
     * 创建Listener监听
     * 
     * @param sseEmitter 连接对象
     * @param messageId  消息id
     * @param timer      请求耗时
     */
    public GlmEventSourceListener(SseEmitter sseEmitter, String messageId, TimeInterval timer) {
        this.sseEmitter = sseEmitter;
        this.messageId = messageId;
        this.timer = timer;
    }

    /**
     * 模型sse建立连接
     * 
     * @param eventSource 事件源
     * @param response    请求
     */
    @Override
    public void onOpen(@NotNull EventSource eventSource, Response response) {
        timer.start(TimerConstant.MODEL_REQUEST_TIME);
        log.info("Model建立sse连接...");
    }

    /**
     * 接收到消息
     * 
     * @param eventSource 事件源
     * @param id          事件id
     * @param type        事件类型
     * @param data        事件内容
     */
    @SneakyThrows
    @Override
    public void onEvent(@NotNull EventSource eventSource, String id, String type, String data) {
        try {
            if ("[DONE]".equals(data)) {
                SseSendUtils.sendSseEvent(sseEmitter, messageId, EventNameType.DONE.getCode(), EventNameType.DONE
                    .getCode());
                sseEmitter.complete();
                return;
            }
            ObjectMapper mapper = new ObjectMapper();
            ChatCompletionResponse completionResponse = mapper.readValue(data, ChatCompletionResponse.class);
            String content = completionResponse.getChoices().get(0).getDelta().getContent();
            if (content != null) {
                last.append(content);
                boolean status = SseSendUtils.sendSseEvent(sseEmitter, messageId, EventNameType.ADD.getCode(), content);
                if (!status) {
                    onClosed(eventSource);
                }
            }
        } catch (Exception e) {
            log.error("SSE消息发送的时候出现异常: {}", e.getMessage(), e);
            //关闭模型sse请求同时断开连接
            onClosed(eventSource);
        }
    }

    /**
     * 模型关闭连接的时候出发此方法
     * 
     * @param eventSource 事件源
     */
    @Override
    public void onClosed(@NotNull EventSource eventSource) {
        log.info("Model sse连接关闭messageId:{}", messageId);
        eventSource.cancel();
        sseEmitter.complete();
    }

    /**
     * 发生异常的时候触发此方法
     * 
     * @param eventSource 事件源
     * @param t           Throwable
     * @param response    请求消息
     */
    @SneakyThrows
    @Override
    public void onFailure(EventSource eventSource, Throwable t, Response response) {
        //        Optional.ofNullable(response).ifPresentOrElse(res -> {
        //            try (ResponseBody body = res.body()) {
        //                if (Objects.nonNull(body)) {
        //                    log.error("Model  sse连接异常data：{}，异常：{}", body.string(), t);
        //                } else {
        //                    log.error("Model  sse连接异常data：{}，异常：{}", res, t);
        //                }
        //            } catch (IOException e) {
        //                log.error("Error reading response body: {}", e.getMessage(), e);
        //            }
        //        }, () -> log.error("Model  sse连接异常data：{}，异常：{}", t));
        log.error("Model  sse连接异常");
        eventSource.cancel();
        sseEmitter.complete();
    }

}
