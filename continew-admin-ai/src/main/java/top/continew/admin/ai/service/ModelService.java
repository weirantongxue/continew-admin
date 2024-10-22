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
import top.continew.admin.ai.model.query.ModelQuery;
import top.continew.admin.ai.model.req.ModelReq;
import top.continew.admin.ai.model.resp.ModelDetailResp;
import top.continew.admin.ai.model.resp.ModelResp;

/**
 * AI模型业务接口
 *
 * @author weiran
 * @since 2024/08/04 23:35
 */
public interface ModelService extends BaseService<ModelResp, ModelDetailResp, ModelQuery, ModelReq> {}