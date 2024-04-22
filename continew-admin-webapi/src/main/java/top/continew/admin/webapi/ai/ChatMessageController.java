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

package top.continew.admin.webapi.ai;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.front.model.query.ChatMessageQuery;
import top.continew.admin.front.model.req.ChatMessageReq;
import top.continew.admin.front.model.resp.ChatMessageDetailResp;
import top.continew.admin.front.model.resp.ChatMessageResp;
import top.continew.admin.front.service.ChatMessageService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.starter.extension.crud.enums.Api;

/**
 * 对话消息管理 API
 *
 * @author weiran
 * @since 2024/03/10 23:15
 */
@Tag(name = "对话消息管理 API")
@RestController
@CrudRequestMapping(value = "/ai/message", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class ChatMessageController extends BaseController<ChatMessageService, ChatMessageResp, ChatMessageDetailResp, ChatMessageQuery, ChatMessageReq> {

}