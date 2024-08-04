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

package top.continew.admin.ai.handler;

import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import top.continew.admin.ai.listener.GlmEventSourceListener;
import top.continew.admin.ai.model.req.MessageRequest;
import top.continew.admin.ai.strategy.ModelStrategy;
import top.continew.admin.ai.utils.ModelMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import top.continew.admin.ai.utils.StreamUtils;
import top.continew.admin.ai.constant.TimerConstant;

/**
 * Created by WeiRan on 2024.07.03 18:40
 */
@Component("glm")
@Slf4j
public class GlmHandler implements ModelStrategy {

    @Override
    public SseEmitter completions(MessageRequest messageCreateValidate) {
        SseEmitter sseEmitter = new SseEmitter(-1L);
        TimeInterval timer = new TimeInterval();
        timer.start(TimerConstant.INTERFACE_RESPONSE_TIME);
        String messageId = IdUtil.fastSimpleUUID();
        //获取模型配置
        ChatCompletion chatCompletion = ModelMessageUtils.convertModelCompletion(messageCreateValidate);
        GlmEventSourceListener glmEventSourceListener = new GlmEventSourceListener(sseEmitter, messageId, timer);
        log.info("开始请求模型:{}", JSONObject.toJSONString(chatCompletion));
        //请求模型
        StreamUtils
            .streamCompletion("https://open.bigmodel.cn/api/paas/v4/chat/completions", "9258a4b118cd7545ea2389bfe07334fc.St00V5LEAYBr7F0b", glmEventSourceListener, JSONObject
                .toJSONString(chatCompletion));
        return sseEmitter;
    }

}
