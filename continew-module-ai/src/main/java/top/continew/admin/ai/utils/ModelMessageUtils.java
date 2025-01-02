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

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import top.continew.admin.ai.model.ChatCompletion;
import top.continew.admin.ai.model.req.MessageRequest;

/**
 * Created by WeiRan on 2023.09.15 15:47
 */
public class ModelMessageUtils {

    /**
     * 转换请求体
     *
     * @return ChatCompletion
     */
    public static ChatCompletion convertModelCompletion(MessageRequest messageRequest) {
        return ChatCompletion.builder()
                .model("glm-4-flash")
                .messages(messageRequest.getMessages())
                .max_tokens(3000)
                .stream(true)
                .temperature(0.2F)
                .top_p(0.2F)
                .build();
    }

    /**
     * 转换给前端返回消息内容
     *
     * @return JSONObject
     */
    public static JSONObject convertModelChatResponse(String id, String content) {
        JSONObject jsonObject = new JSONObject();
        if ( StrUtil.isNotBlank(id) && StrUtil.isNotBlank(content) ) {
            jsonObject.put("id", id);
            jsonObject.put("content", content);
            return jsonObject;
        }
        return jsonObject;
    }

}
