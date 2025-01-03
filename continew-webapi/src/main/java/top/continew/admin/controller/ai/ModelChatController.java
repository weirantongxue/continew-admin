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

package top.continew.admin.controller.ai;

import cn.dev33.satoken.annotation.SaIgnore;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import top.continew.admin.ai.context.ModelContext;
import top.continew.admin.ai.model.chat.req.MessageRequest;
import top.continew.starter.log.annotation.Log;

/**
 * Created by WeiRan on 2024.11.09 00:47
 */
@Log(module = "AI对话")
@Tag(name = "AI对话")
@RestController
@RequiredArgsConstructor
@RequestMapping("/model/chat")
public class ModelChatController {
    private final ModelContext modelContext;

    @SaIgnore
    @Operation(summary = "Ai对话", description = "Ai对话 SSE流式返回")
    @PostMapping(value = "/v1/completions")
    public Flux<ServerSentEvent<JSONObject>> completions(@RequestBody MessageRequest messageRequest) {
        return modelContext.handlerInstance(messageRequest.getModel()).completions(messageRequest);
    }

}
