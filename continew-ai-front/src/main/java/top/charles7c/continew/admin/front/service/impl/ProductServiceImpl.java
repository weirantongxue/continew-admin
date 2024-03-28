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

package top.charles7c.continew.admin.front.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.charles7c.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.charles7c.continew.admin.front.mapper.ProductMapper;
import top.charles7c.continew.admin.front.model.entity.ProductDO;
import top.charles7c.continew.admin.front.model.query.ProductQuery;
import top.charles7c.continew.admin.front.model.req.ProductReq;
import top.charles7c.continew.admin.front.model.resp.ProductDetailResp;
import top.charles7c.continew.admin.front.model.resp.ProductResp;
import top.charles7c.continew.admin.front.service.ProductService;

/**
 * 产品业务实现
 *
 * @author weiran
 * @since 2024/03/28 14:25
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends BaseServiceImpl<ProductMapper, ProductDO, ProductResp, ProductDetailResp, ProductQuery, ProductReq> implements ProductService {}