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

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import top.continew.admin.ai.model.chat.req.MessageRequest;
import top.continew.admin.ai.model.chat.resp.ChatCompletionResponse;
import top.continew.admin.ai.strategy.ModelStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.continew.admin.ai.utils.ModelMessageUtils;

/**
 * Created by WeiRan on 2024.07.03 18:40
 */
@Component("glm")
@Slf4j
@AllArgsConstructor
public class GlmHandler implements ModelStrategy {

    @Override
    public Flux<ServerSentEvent<JSONObject>> completions(MessageRequest messageCreateValidate) {
        WebClient webClient = WebClient.create();
        StringBuffer sb = new StringBuffer();
        return webClient.post()
            .uri("https://open.bigmodel.cn/api/paas/v4/chat/completions") // 三方接口路径
            .header("Authorization", "Bearer 9258a4b118cd7545ea2389bfe07334fc.St00V5LEAYBr7F0b") // 替换为你的API密钥
            .header("Accept", "text/event-stream") // 声明支持SSE
            .bodyValue(ModelMessageUtils.convertModelCompletion(messageCreateValidate)) // 发送请求体
            .retrieve()
            .bodyToFlux(String.class) // 接收流式数据
            .flatMap(data -> {
                if ("[DONE]".equals(data)) {
                    JSONObject jsonObject = new JSONObject();
                    //消息入库
                    System.out.println(sb);
                    // 如果是结束标志
                    return Flux.just(ServerSentEvent.builder(jsonObject)
                        .event("done")
                        .id(IdUtil.fastSimpleUUID())
                        .build());
                }
                // 解析响应内容
                try {
                    ChatCompletionResponse response = JSONObject.parseObject(data, ChatCompletionResponse.class);
                    String content = response.getChoices().get(0).getDelta().getContent();
                    String taskId = response.getId();
                    sb.append(content);
                    return Flux.just(ServerSentEvent.builder(ModelMessageUtils
                        .convertModelChatResponse(taskId, content)).event("add").id(IdUtil.fastSimpleUUID()).build());
                } catch (Exception e) {
                    // 如果解析失败
                    return Flux.just(ServerSentEvent.builder(ModelMessageUtils.convertModelChatResponse(IdUtil
                        .fastSimpleUUID(), "服务异常请联系管理员")).event("error").build());
                }
            })
            .onErrorResume(e -> {
                log.error("Error occurred:{} ", e.getMessage());
                return Flux.just(ServerSentEvent.builder(ModelMessageUtils.convertModelChatResponse(IdUtil
                    .fastSimpleUUID(), "服务异常请联系管理员")).event("error").build());
            });
    }
}
