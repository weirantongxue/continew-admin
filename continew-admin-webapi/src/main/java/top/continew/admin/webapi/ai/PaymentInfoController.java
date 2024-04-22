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

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.text.CharSequenceUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.continew.admin.front.model.query.PaymentInfoQuery;
import top.continew.admin.front.model.resp.PaymentInfoDetailResp;
import top.continew.admin.front.model.resp.PaymentInfoResp;
import top.continew.admin.front.service.PaymentInfoService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.enums.Api;
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.extension.crud.model.query.SortQuery;
import top.continew.starter.extension.crud.model.resp.PageResp;
import top.continew.starter.web.model.R;

import java.util.List;

/**
 * 支付信息管理 API
 *
 * @author weiran
 * @since 2024/03/28 14:30
 */
@Tag(name = "支付信息管理 API")
@RestController
@RequiredArgsConstructor
@CrudRequestMapping(value = "/ai/paymentInfo", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class PaymentInfoController {
    private final PaymentInfoService baseService;

    @Operation(summary = "分页查询列表", description = "分页查询列表")
    @ResponseBody
    @GetMapping
    public R<PageResp<PaymentInfoResp>> page(PaymentInfoQuery query, @Validated PageQuery pageQuery) {
        this.checkPermission(Api.LIST);
        return R.ok(this.baseService.page(query, pageQuery));
    }

    @Operation(summary = "查询列表", description = "查询列表")
    @ResponseBody
    @GetMapping({"/list"})
    public R<List<PaymentInfoResp>> list(PaymentInfoQuery query, SortQuery sortQuery) {
        this.checkPermission(Api.LIST);
        return R.ok(this.baseService.list(query, sortQuery));
    }

    @Operation(summary = "查看详情", description = "查看详情")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @ResponseBody
    @GetMapping({"/{id}"})
    public R<PaymentInfoDetailResp> get(@PathVariable Long id) {
        this.checkPermission(Api.LIST);
        return R.ok(this.baseService.get(id));
    }

    @Operation(summary = "删除数据", description = "删除数据")
    @Parameter(name = "ids", description = "ID 列表", example = "1,2", in = ParameterIn.PATH)
    @ResponseBody
    @DeleteMapping({"/{ids}"})
    public R<Void> delete(@PathVariable List<Long> ids) {
        this.checkPermission(Api.DELETE);
        this.baseService.delete(ids);
        return R.ok("删除成功");
    }

    @Operation(summary = "导出数据", description = "导出数据")
    @GetMapping({"/export"})
    public void export(PaymentInfoQuery query, SortQuery sortQuery, HttpServletResponse response) {
        this.checkPermission(Api.EXPORT);
        this.baseService.export(query, sortQuery, response);
    }

    protected void checkPermission(Api api) {
        CrudRequestMapping crudRequestMapping = (CrudRequestMapping)this.getClass()
            .getDeclaredAnnotation(CrudRequestMapping.class);
        String path = crudRequestMapping.value();
        String permissionPrefix = String.join(":", CharSequenceUtil.splitTrim(path, "/"));
        StpUtil.checkPermission("%s:%s".formatted(permissionPrefix, api.name().toLowerCase()));
    }

}
