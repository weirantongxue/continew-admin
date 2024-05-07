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

package top.continew.admin.front.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.continew.admin.front.mapper.DeptAccountLogMapper;
import top.continew.admin.front.model.entity.DeptAccountLogDO;
import top.continew.admin.front.model.query.DeptAccountLogQuery;
import top.continew.admin.front.model.req.DeptAccountLogReq;
import top.continew.admin.front.model.resp.DeptAccountLogDetailResp;
import top.continew.admin.front.model.resp.DeptAccountLogResp;
import top.continew.admin.front.service.DeptAccountLogService;

/**
 * 账户扣费记录业务实现
 *
 * @author weiran
 * @since 2024/05/07 17:11
 */
@Service
@RequiredArgsConstructor
public class DeptAccountLogServiceImpl extends BaseServiceImpl<DeptAccountLogMapper, DeptAccountLogDO, DeptAccountLogResp, DeptAccountLogDetailResp, DeptAccountLogQuery, DeptAccountLogReq> implements DeptAccountLogService {}