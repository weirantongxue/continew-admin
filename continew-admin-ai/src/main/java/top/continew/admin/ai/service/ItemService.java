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

package top.continew.admin.ai.service;

import top.continew.starter.extension.crud.service.BaseService;
import top.continew.admin.ai.model.query.ItemQuery;
import top.continew.admin.ai.model.req.ItemReq;
import top.continew.admin.ai.model.resp.ItemDetailResp;
import top.continew.admin.ai.model.resp.ItemResp;

/**
 * AI会话业务接口
 *
 * @author weiran
 * @since 2024/08/04 23:37
 */
public interface ItemService extends BaseService<ItemResp, ItemDetailResp, ItemQuery, ItemReq> {}