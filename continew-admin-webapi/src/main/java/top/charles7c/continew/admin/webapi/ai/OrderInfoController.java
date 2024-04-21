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

package top.charles7c.continew.admin.webapi.ai;

import io.swagger.v3.oas.annotations.Operation;
import top.charles7c.continew.admin.front.model.entity.OrderInfoDO;
import top.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.charles7c.continew.admin.front.model.query.OrderInfoQuery;
import top.charles7c.continew.admin.front.model.req.OrderInfoReq;
import top.charles7c.continew.admin.front.model.resp.OrderInfoDetailResp;
import top.charles7c.continew.admin.front.model.resp.OrderInfoResp;
import top.charles7c.continew.admin.front.service.OrderInfoService;
import top.continew.starter.log.core.annotation.Log;
import top.continew.starter.web.model.R;

/**
 * 订单信息管理 API
 *
 * @author weiran
 * @since 2024/03/28 14:25
 */
@Tag(name = "订单信息管理 API")
@RestController
@CrudRequestMapping(value = "/ai/orderInfo", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class OrderInfoController extends BaseController<OrderInfoService, OrderInfoResp, OrderInfoDetailResp, OrderInfoQuery, OrderInfoReq> {

    @Log(ignore = true)
    @Operation(summary = "创建订单", description = "创建订单")
    @ResponseBody
    @GetMapping("/createOrderByProductId")
    public R<OrderInfoDO> createOrderByProductId(Long id) {
        return R.ok(baseService.createOrderByProductId(id));
    }
}