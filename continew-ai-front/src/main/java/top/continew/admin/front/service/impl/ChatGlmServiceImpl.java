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

package top.continew.admin.front.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.continew.admin.common.constant.TimerConstant;
import top.continew.admin.common.enums.EventNameType;
import top.continew.admin.common.model.dto.LoginUser;
import top.continew.admin.common.util.ApiTokenUtils;
import top.continew.admin.common.util.StreamUtils;
import top.continew.admin.common.util.WsUtils;
import top.continew.admin.common.util.helper.LoginHelper;
import top.continew.admin.front.listener.GPTEventSourceListener;
import top.continew.admin.front.model.ChatMessageUtils;
import top.continew.admin.front.model.entity.ChatMessageDO;
import top.continew.admin.front.model.resp.ModelDetailResp;
import top.continew.admin.front.model.resp.ModelScriptDetailResp;
import top.continew.admin.front.model.validate.ChatMessageRequestValidate;
import top.continew.admin.front.model.vo.DeptAccountVo;
import top.continew.admin.front.service.*;

/**
 * Created by WeiRan on 2023.09.22 21:17
 */
@Component()
@RequiredArgsConstructor
@Slf4j
public class ChatGlmServiceImpl implements ChatGlmService {
    private final ChatMessageService chatMessageService;
    private final ModelService modelService;
    private final ModelScriptService modelScriptService;
    private final DeptAccountService deptAccountService;

    @Override
    public void aiApi(ChatMessageRequestValidate messageCreateValidate, String userId) {
        try {
            TimeInterval timer = new TimeInterval();
            timer.start(TimerConstant.RESPONSE_TIME);
            String messageId = IdUtil.fastSimpleUUID();
            LoginUser loginUser = LoginHelper.getLoginUser(StpUtil.getTokenValueByLoginId(userId));
            //判断账户余额
            DeptAccountVo deptAccountVo = deptAccountService.selectBalance(loginUser.getDeptId());
            if (deptAccountVo.getBalanceToken() <= 0) {
                WsUtils.sendToUser(userId, ChatMessageUtils
                    .chatModelMsg(messageId, userId, "学校账号余额不足,请联系管理员充值", EventNameType.ERROR.getCode()));
                WsUtils.close(WsUtils.getWebSocketSession(userId), "余额不足,关闭链接");
                return;
            }
            ModelDetailResp modelDetailResp = modelService.get(messageCreateValidate.getModelId());
            ModelScriptDetailResp modelScriptDetailResp = modelScriptService.get(messageCreateValidate
                .getModelScriptId());
            ChatMessageDO message = ChatMessageUtils
                .convertMessageUtils(messageCreateValidate, modelDetailResp, messageId, userId);

            GPTEventSourceListener gptEventSourceListener = new GPTEventSourceListener(userId, messageId, chatMessageService, message, timer, loginUser
                .getDeptId());
            String authToken = ApiTokenUtils.generateClientToken(modelDetailResp.getApiKey());
            StreamUtils.streamCompletion(modelDetailResp.getUrl(), authToken, gptEventSourceListener, ChatMessageUtils
                .convertModelRequest(messageCreateValidate, modelDetailResp, modelScriptDetailResp));
        } catch (Exception e) {
            log.error("Glm6B请求失败");
        }
    }

}
