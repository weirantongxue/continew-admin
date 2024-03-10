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
import com.alibaba.fastjson2.JSONObject;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import top.charles7c.continew.admin.front.constant.TimerConstant;
import top.charles7c.continew.admin.front.enums.EventNameType;
import top.charles7c.continew.admin.front.model.entity.MessageDO;
import top.charles7c.continew.admin.front.service.MessageService;

import java.util.Objects;

/**
 * Created by WeiRan on 2023.09.15 12:08
 */
@Slf4j
public class GLMEventSourceListener extends EventSourceListener {

    private final SseEmitter sseEmitter;
    private final String messageId;
    private final MessageService messageService;

    private final MessageDO message;

    private final TimeInterval timer;

    private String last = "";

    public GLMEventSourceListener(SseEmitter sseEmitter,
                                  String messageId,
                                  MessageService messageService,
                                  MessageDO message,
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
        if ("finish".equals(type)) {
            //messageService.insertMessage(MessageUtils.setMessageDO(message,last, timer.intervalMs(TimerConstant.RESPONSE_TIME), timer.intervalMs(TimerConstant.CHAT_RESPONSE_TIME)));
            sseEmitter.send(SseEmitter.event()
                .id(messageId)
                .name(EventNameType.FINISH.getCode())
                .data(EventNameType.DONE.getCode())
                .reconnectTime(3000));
            sseEmitter.complete();
            return;
        }
        if (data != null) {
            last = last + data;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("content", data);
            sseEmitter.send(SseEmitter.event()
                .id(messageId)
                .name(EventNameType.ADD.getCode())
                .data(jsonObject)
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
            log.error("ChatGlm  sse连接异常data：{}，异常：{}", body.string(), t);
        } else {
            log.error("ChatGlm  sse连接异常data：{}，异常：{}", response, t);
        }
        eventSource.cancel();
        sseEmitter.complete();
    }

}
