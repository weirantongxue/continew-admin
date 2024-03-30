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

package top.charles7c.continew.admin.front.service;

import top.charles7c.continew.starter.extension.crud.service.BaseService;
import top.charles7c.continew.admin.front.model.query.PayConfigQuery;
import top.charles7c.continew.admin.front.model.req.PayConfigReq;
import top.charles7c.continew.admin.front.model.resp.PayConfigDetailResp;
import top.charles7c.continew.admin.front.model.resp.PayConfigResp;

/**
 * 支付配置业务接口
 *
 * @author weiran
 * @since 2024/03/30 16:11
 */
public interface PayConfigService extends BaseService<PayConfigResp, PayConfigDetailResp, PayConfigQuery, PayConfigReq> {}