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

package top.charles7c.continew.admin.webapi.ai;

import cn.dev33.satoken.annotation.SaIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import top.charles7c.continew.admin.front.context.ChatContext;
import top.charles7c.continew.admin.front.model.validate.ChatMessageRequestValidate;
import top.charles7c.continew.starter.log.core.annotation.Log;

/**
 * Created by WeiRan on 2023.09.08 11:36
 */

//@Tag(name = "chat对话")
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatContext chatContext;


    //@Operation(summary = "chat对话", description = "chat对话")
    @Operation
    @Hidden
    @Log
    @PostMapping(value = "/v1/completions")
    public SseEmitter completions(@RequestBody ChatMessageRequestValidate createValidate) {
        return chatContext.handlerInstance(createValidate.getModel()).aiApi(createValidate);
    }

}
