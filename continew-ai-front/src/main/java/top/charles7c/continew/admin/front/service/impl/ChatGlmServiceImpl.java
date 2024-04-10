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

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.charles7c.continew.admin.common.constant.TimerConstant;
import top.charles7c.continew.admin.common.model.dto.LoginUser;
import top.charles7c.continew.admin.common.util.ApiTokenUtils;
import top.charles7c.continew.admin.common.util.StreamUtils;
import top.charles7c.continew.admin.common.util.helper.LoginHelper;
import top.charles7c.continew.admin.front.listener.GPTEventSourceListener;
import top.charles7c.continew.admin.front.model.ChatMessageUtils;
import top.charles7c.continew.admin.front.model.entity.ChatMessageDO;
import top.charles7c.continew.admin.front.model.resp.ModelDetailResp;
import top.charles7c.continew.admin.front.model.resp.ModelScriptDetailResp;
import top.charles7c.continew.admin.front.model.validate.ChatMessageRequestValidate;
import top.charles7c.continew.admin.front.service.*;

/**
 * Created by WeiRan on 2023.09.22 21:17
 */
@Component()
@RequiredArgsConstructor
@Slf4j
public class ChatGlmServiceImpl implements ChatGlmService {
    private final ChatMessageService chatMessageService;
    private final WebSocketSendService webSocketSendService;
    private final ModelService modelService;
    private final ModelScriptService modelScriptService;

    @Override
    public void aiApi(ChatMessageRequestValidate messageCreateValidate, String sessionId) {
        try {
            TimeInterval timer = new TimeInterval();
            timer.start(TimerConstant.RESPONSE_TIME);
            String messageId = IdUtil.fastSimpleUUID();

            ModelDetailResp modelDetailResp = modelService.get(messageCreateValidate.getModelId());
            ModelScriptDetailResp modelScriptDetailResp = modelScriptService.get(messageCreateValidate
                .getModelScriptId());
            ChatMessageDO message = ChatMessageUtils
                .convertMessageUtils(messageCreateValidate, modelDetailResp, messageId, sessionId);
            LoginUser loginUser = LoginHelper.getLoginUser(StpUtil.getTokenValueByLoginId(sessionId));
            GPTEventSourceListener gptEventSourceListener = new GPTEventSourceListener(webSocketSendService, sessionId, messageId, chatMessageService, message, timer, loginUser
                .getDeptId());
            String authToken = ApiTokenUtils.generateClientToken("9258a4b118cd7545ea2389bfe07334fc.St00V5LEAYBr7F0b");

            StreamUtils
                .streamCompletion("https://open.bigmodel.cn/api/paas/v4/chat/completions", authToken, gptEventSourceListener, ChatMessageUtils
                    .convertModelRequest(messageCreateValidate, modelDetailResp, modelScriptDetailResp));
        } catch (Exception e) {
            log.error("Glm6B请求失败");
        }
    }
}
