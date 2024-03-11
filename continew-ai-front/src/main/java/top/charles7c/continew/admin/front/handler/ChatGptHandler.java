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

package top.charles7c.continew.admin.front.handler;

import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.IdUtil;
import com.unfbx.chatgpt.OpenAiStreamClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import top.charles7c.continew.admin.front.constant.TimerConstant;
import top.charles7c.continew.admin.front.listener.GPTEventSourceListener;
import top.charles7c.continew.admin.front.model.entity.ChatMessageDO;
import top.charles7c.continew.admin.front.model.validate.ChatMessageRequestValidate;
import top.charles7c.continew.admin.front.service.ChatMessageService;
import top.charles7c.continew.admin.front.strategy.ChatStrategy;
import top.charles7c.continew.admin.front.util.ChatMessageUtils;

/**
 * Created by WeiRan on 2023.09.08 11:36
 */
@Component("gpt-3.5-turbo")
@Slf4j
public class ChatGptHandler implements ChatStrategy {
    private final OpenAiStreamClient openAiStreamClient;

    @Resource
    private ChatMessageService messageService;

    public ChatGptHandler(OpenAiStreamClient openAiStreamClient) {
        this.openAiStreamClient = openAiStreamClient;
    }

    @Override
    public SseEmitter aiApi(ChatMessageRequestValidate messageRequestValidate) {
        SseEmitter sseEmitter = new SseEmitter(-1L);
        try {
            TimeInterval timer = new TimeInterval();
            timer.start(TimerConstant.RESPONSE_TIME);
            String messageId = IdUtil.fastSimpleUUID();

            ChatMessageDO message = ChatMessageUtils.ConvertMessageUtils(messageRequestValidate, messageId);

            GPTEventSourceListener gptEventSourceListener = new GPTEventSourceListener(sseEmitter, messageId, messageService, message, timer);
            ChatCompletion completion = ChatCompletion.builder()
                .messages(messageRequestValidate.getMessages())
                .model(messageRequestValidate.getModel())
                .stream(true)
                .build();
            openAiStreamClient.streamChatCompletion(completion, gptEventSourceListener);

        } catch (Exception e) {
            log.error("Openai请求失败");
        }
        return sseEmitter;
    }

}
