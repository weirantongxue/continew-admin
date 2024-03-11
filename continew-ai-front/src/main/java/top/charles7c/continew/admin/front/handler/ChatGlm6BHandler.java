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
import com.alibaba.fastjson2.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import top.charles7c.continew.admin.front.constant.TimerConstant;
import top.charles7c.continew.admin.front.listener.GPTEventSourceListener;
import top.charles7c.continew.admin.front.model.entity.ChatMessageDO;
import top.charles7c.continew.admin.front.model.validate.ChatMessageRequestValidate;
import top.charles7c.continew.admin.front.service.ChatMessageService;
import top.charles7c.continew.admin.front.strategy.ChatStrategy;
import top.charles7c.continew.admin.front.util.ApiTokenUtils;
import top.charles7c.continew.admin.front.util.ChatMessageUtils;
import top.charles7c.continew.admin.front.util.StreamUtils;

/**
 * Created by WeiRan on 2023.09.22 21:17
 */
@Component("glm-4")
@RequiredArgsConstructor
@Slf4j
public class ChatGlm6BHandler implements ChatStrategy {
    private final ChatMessageService messageService;

    @Override
    public SseEmitter aiApi(ChatMessageRequestValidate messageCreateValidate) {
        SseEmitter sseEmitter = new SseEmitter(-1L);
        try {
            TimeInterval timer = new TimeInterval();
            timer.start(TimerConstant.RESPONSE_TIME);
            String messageId = IdUtil.fastSimpleUUID();
            ChatMessageDO message = ChatMessageUtils.ConvertMessageUtils(messageCreateValidate, messageId);
            GPTEventSourceListener gptEventSourceListener = new GPTEventSourceListener(sseEmitter, messageId, messageService, message, timer);
            String authToken = ApiTokenUtils.generateClientToken("9258a4b118cd7545ea2389bfe07334fc.St00V5LEAYBr7F0b");
            System.out.println(authToken);
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(messageCreateValidate));
            //jsonObject.put("prompt", jsonObject.remove("messages"));
            System.out.println("json");
            StreamUtils
                .streamCompletion("https://open.bigmodel.cn/api/paas/v4/chat/completions", authToken, gptEventSourceListener, JSONObject
                    .toJSONString(jsonObject));
        } catch (Exception e) {
            log.error("Glm6B请求失败");
        }
        return sseEmitter;
    }

}
