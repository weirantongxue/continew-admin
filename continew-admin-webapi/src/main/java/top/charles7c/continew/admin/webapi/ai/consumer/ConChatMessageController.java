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

package top.charles7c.continew.admin.webapi.ai.consumer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.charles7c.continew.admin.common.util.helper.LoginHelper;
import top.charles7c.continew.admin.front.model.query.ChatMessageQuery;
import top.charles7c.continew.admin.front.model.resp.ChatMessageResp;
import top.charles7c.continew.admin.front.service.ChatMessageService;
import top.charles7c.continew.starter.extension.crud.model.query.PageQuery;
import top.charles7c.continew.starter.extension.crud.model.resp.PageResp;
import top.charles7c.continew.starter.web.model.R;

/**
 * 对话消息管理 API
 *
 * @author weiran
 * @since 2024/03/10 23:15
 */
@Tag(name = "用户对话消息管理 API")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/ai/message/consumer")
public class ConChatMessageController {
    private final ChatMessageService baseService;

    @Operation(summary = "用户消息查询", description = "用户消息查询")
    @ResponseBody
    @GetMapping(value = "/userMessage")
    public R<PageResp<ChatMessageResp>> page(ChatMessageQuery query, @Validated PageQuery pageQuery) {
        query.setCreateUser(LoginHelper.getUserId());
        pageQuery.setSort(new String[]{"create_time", "desc"});
        return R.ok(this.baseService.page(query, pageQuery));
    }

}