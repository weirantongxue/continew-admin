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

package top.charles7c.continew.admin.front.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.charles7c.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.charles7c.continew.admin.front.mapper.MessageMapper;
import top.charles7c.continew.admin.front.model.entity.MessageDO;
import top.charles7c.continew.admin.front.model.query.MessageQuery;
import top.charles7c.continew.admin.front.model.req.MessageReq;
import top.charles7c.continew.admin.front.model.resp.MessageDetailResp;
import top.charles7c.continew.admin.front.model.resp.MessageResp;
import top.charles7c.continew.admin.front.service.MessageService;

/**
 * 对话消息业务实现
 *
 * @author weiran
 * @since 2024/03/10 23:15
 */
@Service
@RequiredArgsConstructor
public class MessageServiceImpl extends BaseServiceImpl<MessageMapper, MessageDO, MessageResp, MessageDetailResp, MessageQuery, MessageReq> implements MessageService {}