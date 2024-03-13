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

import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import top.charles7c.continew.admin.common.constant.TimerConstant;
import top.charles7c.continew.admin.common.util.ApiTokenUtils;
import top.charles7c.continew.admin.common.util.StreamUtils;
import top.charles7c.continew.admin.front.listener.GPTEventSourceListener;
import top.charles7c.continew.admin.front.model.ChatMessageUtils;
import top.charles7c.continew.admin.front.model.entity.ChatMessageDO;
import top.charles7c.continew.admin.front.model.validate.ChatMessageRequestValidate;
import top.charles7c.continew.admin.front.service.ChatGlmService;
import top.charles7c.continew.admin.front.service.ChatMessageService;
import top.charles7c.continew.admin.front.service.WebSocketSendService;

/**
 * Created by WeiRan on 2023.09.22 21:17
 */
@Component()
@RequiredArgsConstructor
@Slf4j
public class ChatGlmServiceImpl implements ChatGlmService {
    private final ChatMessageService chatMessageService;
    private final WebSocketSendService webSocketSendService;


    @Override
    public void aiApi(ChatMessageRequestValidate messageCreateValidate,String sessionId) {
        try {
            TimeInterval timer = new TimeInterval();
            timer.start(TimerConstant.RESPONSE_TIME);
            String messageId = IdUtil.fastSimpleUUID();

            ChatMessageDO message = ChatMessageUtils.ConvertMessageUtils(messageCreateValidate, messageId);



            GPTEventSourceListener gptEventSourceListener = new GPTEventSourceListener(webSocketSendService,sessionId,messageId, chatMessageService, message, timer);
            String authToken = ApiTokenUtils.generateClientToken("9258a4b118cd7545ea2389bfe07334fc.St00V5LEAYBr7F0b");

            messageCreateValidate.setModel("glm-4");
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(messageCreateValidate));
            System.out.println("开始请求模型"+jsonObject);
            StreamUtils
                    .streamCompletion("https://open.bigmodel.cn/api/paas/v4/chat/completions", authToken, gptEventSourceListener, JSONObject
                            .toJSONString(jsonObject));
        } catch (Exception e) {
            log.error("Glm6B请求失败");
        }
    }

}
