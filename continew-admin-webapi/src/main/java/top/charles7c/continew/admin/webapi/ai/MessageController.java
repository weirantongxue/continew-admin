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

import top.charles7c.continew.admin.front.model.query.MessageQuery;
import top.charles7c.continew.admin.front.model.req.MessageReq;
import top.charles7c.continew.admin.front.model.resp.MessageDetailResp;
import top.charles7c.continew.admin.front.model.resp.MessageResp;
import top.charles7c.continew.admin.front.service.MessageService;
import top.charles7c.continew.starter.extension.crud.controller.BaseController;
import top.charles7c.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.charles7c.continew.starter.extension.crud.annotation.CrudRequestMapping;

/**
 * 对话消息管理 API
 *
 * @author weiran
 * @since 2024/03/10 23:15
 */
@Tag(name = "对话消息管理 API")
@RestController
@CrudRequestMapping(value = "/front/message", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class MessageController extends BaseController<MessageService, MessageResp, MessageDetailResp, MessageQuery, MessageReq> {}