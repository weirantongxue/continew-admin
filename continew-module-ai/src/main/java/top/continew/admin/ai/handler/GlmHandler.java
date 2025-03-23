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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
        // 使用单例的 WebClient（可在 Spring Bean 中定义）
        WebClient webClient = WebClient.builder()
                .baseUrl("https://open.bigmodel.cn/api/paas/v4/chat/completions")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer 9258a4b118cd7545ea2389bfe07334fc.St00V5LEAYBr7F0b") // API密钥（建议从配置文件读取）
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.TEXT_EVENT_STREAM_VALUE) // 声明支持SSE
                .build();
        StringBuilder contentBuilder = new StringBuilder();

        return webClient.post()
                .bodyValue(ModelMessageUtils.convertModelCompletion(messageCreateValidate)) // 发送请求体
                .retrieve()
                .bodyToFlux(String.class) // 接收流式数据
                .flatMap(data -> {
                    try {
                        // 结束标志处理
                        if ( "[DONE]".equals(data) ) {
                            log.info("SSE 消息接收完成，完整消息: {}", contentBuilder);
                            return Flux.just(ServerSentEvent.<JSONObject>builder()
                                    .event("done")
                                    .id(IdUtil.fastSimpleUUID())
                                    .data(new JSONObject())
                                    .build());
                        }

                        // 解析响应数据
                        ChatCompletionResponse response = JSONObject.parseObject(data, ChatCompletionResponse.class);
                        String content = response.getChoices().get(0).getDelta().getContent();
                        String taskId = response.getId();
                        contentBuilder.append(content);

                        return Flux.just(ServerSentEvent.<JSONObject>builder()
                                .event("add")
                                .id(IdUtil.fastSimpleUUID())
                                .data(ModelMessageUtils.convertModelChatResponse(taskId, content))
                                .build());
                    } catch (Exception e) {
                        log.error("解析 SSE 响应失败: {}", data, e);
                        return Flux.just(ServerSentEvent.<JSONObject>builder()
                                .event("error")
                                .id(IdUtil.fastSimpleUUID())
                                .data(ModelMessageUtils.convertModelChatResponse(IdUtil.fastSimpleUUID(), "服务异常请联系管理员"))
                                .build());
                    }
                })
                .onErrorResume(e -> {
                    log.error("SSE 请求处理异常", e);
                    return Flux.just(ServerSentEvent.<JSONObject>builder()
                            .event("error")
                            .id(IdUtil.fastSimpleUUID())
                            .data(ModelMessageUtils.convertModelChatResponse(IdUtil.fastSimpleUUID(), "服务异常请联系管理员"))
                            .build());
                });
    }

}
