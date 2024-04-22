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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.front.model.query.ProductQuery;
import top.continew.admin.front.model.req.ProductReq;
import top.continew.admin.front.model.resp.ProductDetailResp;
import top.continew.admin.front.model.resp.ProductResp;
import top.continew.admin.front.service.ProductService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.starter.extension.crud.enums.Api;
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.log.core.annotation.Log;
import top.continew.starter.web.model.R;

import java.util.List;

/**
 * 产品管理 API
 *
 * @author weiran
 * @since 2024/03/28 14:29
 */
@Tag(name = "产品管理 API")
@RestController
@CrudRequestMapping(value = "/ai/product", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class ProductController extends BaseController<ProductService, ProductResp, ProductDetailResp, ProductQuery, ProductReq> {

    @Log(ignore = true)
    @Operation(summary = "产品信息", description = "产品信息")
    @ResponseBody
    @GetMapping("/productList")
    public R<List<ProductResp>> coursesInfoList(ProductQuery productQuery, @Validated PageQuery pageQuery) {
        pageQuery.setSort(new String[] {"sort,asc"});
        return R.ok(baseService.list(productQuery, pageQuery));
    }

}