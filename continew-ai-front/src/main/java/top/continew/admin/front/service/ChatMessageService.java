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

package top.continew.admin.front.service;

import top.continew.admin.front.model.entity.ChatMessageDO;
import top.continew.admin.front.model.query.ChatMessageQuery;
import top.continew.admin.front.model.req.ChatMessageReq;
import top.continew.admin.front.model.resp.ChatMessageDetailResp;
import top.continew.admin.front.model.resp.ChatMessageResp;
import top.continew.starter.extension.crud.service.BaseService;

/**
 * 对话消息业务接口
 *
 * @author weiran
 * @since 2024/03/10 23:15
 */
public interface ChatMessageService extends BaseService<ChatMessageResp, ChatMessageDetailResp, ChatMessageQuery, ChatMessageReq> {
    int insertMessage(ChatMessageDO chatMessageDO, Long deptId);
}