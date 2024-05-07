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

package top.continew.admin.webapi.ai;

import top.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.admin.front.model.query.DeptAccountLogQuery;
import top.continew.admin.front.model.req.DeptAccountLogReq;
import top.continew.admin.front.model.resp.DeptAccountLogDetailResp;
import top.continew.admin.front.model.resp.DeptAccountLogResp;
import top.continew.admin.front.service.DeptAccountLogService;

/**
 * 账户扣费记录管理 API
 *
 * @author weiran
 * @since 2024/05/07 17:11
 */
@Tag(name = "账户扣费记录管理 API")
@RestController
@CrudRequestMapping(value = "/ai/deptAccountLog", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE,
    Api.EXPORT})
public class DeptAccountLogController extends BaseController<DeptAccountLogService, DeptAccountLogResp, DeptAccountLogDetailResp, DeptAccountLogQuery, DeptAccountLogReq> {}