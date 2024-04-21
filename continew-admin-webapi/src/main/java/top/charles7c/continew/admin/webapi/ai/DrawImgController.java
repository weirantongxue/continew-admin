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

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import top.charles7c.continew.admin.front.model.query.DrawImgQuery;
import top.charles7c.continew.admin.front.model.req.DrawImgReq;
import top.charles7c.continew.admin.front.model.resp.DrawImgDetailResp;
import top.charles7c.continew.admin.front.model.resp.DrawImgResp;
import top.charles7c.continew.admin.front.service.DrawImgService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.starter.extension.crud.enums.Api;

/**
 * 绘图素材管理 API
 *
 * @author weiran
 * @since 2024/03/16 03:22
 */
@Tag(name = "绘图素材管理 API")
@RestController
@CrudRequestMapping(value = "/ai/drawImg", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class DrawImgController extends BaseController<DrawImgService, DrawImgResp, DrawImgDetailResp, DrawImgQuery, DrawImgReq> {}