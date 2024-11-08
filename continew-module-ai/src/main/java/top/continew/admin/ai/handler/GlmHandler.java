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

import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import top.continew.admin.ai.model.req.MessageRequest;
import top.continew.admin.ai.strategy.ModelStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by WeiRan on 2024.07.03 18:40
 */
@Component("glm")
@Slf4j
@AllArgsConstructor
public class GlmHandler implements ModelStrategy {
    //    private final WebClient webClient;

    @Override
    public Flux<String> completions(MessageRequest messageCreateValidate) {
        WebClient webClient = WebClient.create();
        return webClient.post()
            .uri("https://open.bigmodel.cn/api/paas/v4/chat/completions") // 修改为你要调用的API路径
            .header("Authorization", "9258a4b118cd7545ea2389bfe07334fc.St00V5LEAYBr7F0b") // 替换为你的API密钥
            .bodyValue(messageCreateValidate) // 发送请求体
            .retrieve()
            .bodyToFlux(String.class) // 根据API返回类型进行转换
            .onErrorReturn("Error occurred");
    }

}
