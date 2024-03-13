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

package top.charles7c.continew.admin.front.model;

import cn.hutool.extra.servlet.JakartaServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import top.charles7c.continew.admin.common.model.resp.ChatModelMsg;
import top.charles7c.continew.admin.front.model.entity.ChatMessageDO;
import top.charles7c.continew.admin.front.model.validate.ChatMessageRequestValidate;
import top.charles7c.continew.starter.web.util.ServletUtils;

import java.time.LocalDateTime;

/**
 * Created by WeiRan on 2023.09.15 15:47
 */
public class ChatMessageUtils {

    public static ChatMessageDO ConvertMessageUtils(ChatMessageRequestValidate messageRequestValidate,
                                                    String messageId) {
        //HttpServletRequest request = ServletUtils.getRequest();
        ChatMessageDO message = new ChatMessageDO();
        message.setMessageId(messageId);
        message.setItemId(messageRequestValidate.getItemId());
        message.setQuestion(messageRequestValidate.getMessages()
                .get(messageRequestValidate.getMessages().size() - 1)
                .getContent());
        message.setModel(messageRequestValidate.getModel());
        message.setIp("1231231");
        message.setCreateUser(1L);
        return message;
    }

    public static ChatMessageDO setMessageDO(ChatMessageDO message,
                                             String last,
                                             Long responseTime,
                                             Long chatResponseTime) {
        message.setAnswer(last);
        message.setResponseTime(responseTime);
        message.setChatResponseTime(chatResponseTime);
        message.setCreateTime(LocalDateTime.now());
        return message;
    }

    public static ChatModelMsg chatModelMsg(String msgId, String itemId, String content, String eventType) {
        ChatModelMsg chatModelMsg = new ChatModelMsg();
        chatModelMsg.setMsgId(msgId);
        chatModelMsg.setItemId(itemId);
        chatModelMsg.setContent(content);
        chatModelMsg.setEventType(eventType);
        return chatModelMsg;
    }

}
