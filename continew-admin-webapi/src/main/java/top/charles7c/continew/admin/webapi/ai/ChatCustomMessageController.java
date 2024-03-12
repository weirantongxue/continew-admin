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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.charles7c.continew.admin.common.util.helper.LoginHelper;
import top.charles7c.continew.admin.front.model.query.ChatMessageQuery;
import top.charles7c.continew.admin.front.model.resp.ChatMessageResp;
import top.charles7c.continew.admin.front.service.ChatMessageService;
import top.charles7c.continew.starter.extension.crud.annotation.CrudRequestMapping;
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
@CrudRequestMapping(value = "/custom/message")
public class ChatCustomMessageController {
    private final ChatMessageService chatMessageService;

    @Operation(summary = "分页查询列表", description = "分页查询列表")
    @ResponseBody
    @GetMapping("/list")
    public R<PageResp<ChatMessageResp>> page(@RequestParam(name = "itemId") String itemId,
                                             @Validated PageQuery pageQuery) {
        ChatMessageQuery query = new ChatMessageQuery();
        query.setItemId(itemId);
        query.setCreateUser(LoginHelper.getUserId());
        return R.ok(this.chatMessageService.page(query, pageQuery));
    }

}