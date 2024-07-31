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
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import top.continew.admin.ai.context.ModelContext;
import top.continew.admin.ai.model.req.MessageRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/model")
public class ModelController {

    private final ModelContext modelContext;

    @PostMapping("/v1/completions")
    @SaIgnore
    public SseEmitter completions(@RequestBody MessageRequest messageRequest) {
        return modelContext.handlerInstance(messageRequest.getModel()).completions(messageRequest);
    }

}
