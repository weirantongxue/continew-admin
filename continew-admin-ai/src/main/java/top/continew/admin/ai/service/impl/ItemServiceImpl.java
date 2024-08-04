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
import top.continew.admin.ai.mapper.ItemMapper;
import top.continew.admin.ai.model.entity.ItemDO;
import top.continew.admin.ai.model.query.ItemQuery;
import top.continew.admin.ai.model.req.ItemReq;
import top.continew.admin.ai.model.resp.ItemDetailResp;
import top.continew.admin.ai.model.resp.ItemResp;
import top.continew.admin.ai.service.ItemService;

/**
 * AI会话业务实现
 *
 * @author weiran
 * @since 2024/08/04 23:37
 */
@Service
@RequiredArgsConstructor
public class ItemServiceImpl extends BaseServiceImpl<ItemMapper, ItemDO, ItemResp, ItemDetailResp, ItemQuery, ItemReq> implements ItemService {}