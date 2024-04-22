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

import top.continew.admin.front.model.query.ModelQuery;
import top.continew.admin.front.model.req.ModelReq;
import top.continew.admin.front.model.resp.ModelDetailResp;
import top.continew.admin.front.model.resp.ModelResp;
import top.continew.starter.extension.crud.service.BaseService;

/**
 * AI模型业务接口
 *
 * @author weiran
 * @since 2024/03/21 14:48
 */
public interface ModelService extends BaseService<ModelResp, ModelDetailResp, ModelQuery, ModelReq> {}