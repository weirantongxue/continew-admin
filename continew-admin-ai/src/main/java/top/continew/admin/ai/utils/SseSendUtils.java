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

package top.continew.admin.ai.utils;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Slf4j
public class SseSendUtils {

    public static boolean sendSseEvent(SseEmitter sseEmitter, String messageId, String eventName, String data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("text", data);
        try {
            sseEmitter.send(SseEmitter.event().id(messageId).name(eventName).data(jsonObject).reconnectTime(3000));
            return true;
        } catch (IOException e) {
            log.error("SSE消息发送失败,用户连接已断开");
            return false;
        }
    }
}
