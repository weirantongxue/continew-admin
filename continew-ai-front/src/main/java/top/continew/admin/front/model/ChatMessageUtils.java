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

package top.continew.admin.front.model;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.unfbx.chatgpt.entity.chat.Message;
import top.continew.admin.common.model.resp.ChatModelMsg;
import top.continew.admin.front.model.entity.ChatMessageDO;
import top.continew.admin.front.model.resp.ModelDetailResp;
import top.continew.admin.front.model.resp.ModelScriptDetailResp;
import top.continew.admin.front.model.validate.ChatMessageRequestValidate;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by WeiRan on 2023.09.15 15:47
 */
public class ChatMessageUtils {

    public static ChatMessageDO convertMessageUtils(ChatMessageRequestValidate messageRequestValidate,
                                                    ModelDetailResp modelDetailResp,
                                                    String messageId,
                                                    String sessionId) {

        ChatMessageDO message = new ChatMessageDO();
        message.setMessageId(messageId);
        message.setItemId(messageRequestValidate.getItemId());
        message.setQuestion(messageRequestValidate.getMessages()
            .get(messageRequestValidate.getMessages().size() - 1)
            .getContent());
        message.setModel(modelDetailResp.getName());
        message.setIp("0");
        message.setCreateUser(Long.valueOf(sessionId));
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

    public static String convertModelRequest(ChatMessageRequestValidate messageCreateValidate,
                                             ModelDetailResp modelDetailResp,
                                             ModelScriptDetailResp modelScriptDetailResp) {
        JSONObject jsonObject = new JSONObject();
        List<Message> messageList = messageCreateValidate.getMessages();
        //添加预设prompt
        if (ObjectUtil.isNotEmpty(modelScriptDetailResp) && StrUtil.isNotBlank(modelScriptDetailResp.getPrompt())) {
            Message message = new Message();
            message.setRole("system");
            message.setContent(modelScriptDetailResp.getPrompt());
            messageList.add(0, message);
        }
        jsonObject.put("model", modelDetailResp.getName());
        jsonObject.put("max_tokens", 3000);
        jsonObject.put("messages", messageList);
        jsonObject.put("stream", true);
        return jsonObject.toJSONString();
    }

    public static String chatModelMsg(String msgId, String itemId, String content, String eventType) {
        ChatModelMsg chatModelMsg = new ChatModelMsg();
        chatModelMsg.setMsgId(msgId);
        chatModelMsg.setItemId(itemId);
        chatModelMsg.setContent(content);
        chatModelMsg.setEventType(eventType);
        return JSONObject.toJSONString(chatModelMsg);
    }

}
