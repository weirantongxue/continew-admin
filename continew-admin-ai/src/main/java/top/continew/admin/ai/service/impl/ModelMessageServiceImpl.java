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

package top.continew.admin.ai.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.continew.admin.ai.mapper.ModelMessageMapper;
import top.continew.admin.ai.model.entity.ModelMessageDO;
import top.continew.admin.ai.model.query.ModelMessageQuery;
import top.continew.admin.ai.model.req.ModelMessageReq;
import top.continew.admin.ai.model.resp.ModelMessageDetailResp;
import top.continew.admin.ai.model.resp.ModelMessageResp;
import top.continew.admin.ai.service.ModelMessageService;

/**
 * 对话消息业务实现
 *
 * @author weiran
 * @since 2024/08/04 23:38
 */
@Service
@RequiredArgsConstructor
public class ModelMessageServiceImpl extends BaseServiceImpl<ModelMessageMapper, ModelMessageDO, ModelMessageResp, ModelMessageDetailResp, ModelMessageQuery, ModelMessageReq> implements ModelMessageService {}