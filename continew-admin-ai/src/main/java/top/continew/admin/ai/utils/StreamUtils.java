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
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.EventSources;

import java.util.Optional;

/**
 * Created by WeiRan on 2023.09.22 23:27
 */
@Slf4j
public class StreamUtils {

    public static void streamCompletion(String url,
                                        String authToken,
                                        EventSourceListener eventSourceListener,
                                        String requestBody) {
        try {
            var formBody = RequestBody.create(requestBody, MediaType.parse("application/json; charset=utf-8"));
            var requestBuilder = new Request.Builder();
            Optional.ofNullable(authToken)
                .filter(StrUtil::isNotBlank)
                .ifPresent(token -> requestBuilder.addHeader("Authorization", token));
            var request = requestBuilder.url(url).post(formBody).build();
            var factory = EventSources.createFactory(OkHttpUtils.getInstance());
            //创建事件
            factory.newEventSource(request, eventSourceListener);
        } catch (Exception e) {
            log.error("请求streamCompletion异常：{}", e.getMessage(), e);
        }
    }
}