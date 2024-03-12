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

import cn.hutool.core.util.IdUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.charles7c.continew.admin.common.util.helper.LoginHelper;
import top.charles7c.continew.admin.front.model.query.ItemQuery;
import top.charles7c.continew.admin.front.model.req.ItemReq;
import top.charles7c.continew.admin.front.model.resp.ItemResp;
import top.charles7c.continew.admin.front.service.ItemService;
import top.charles7c.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.charles7c.continew.starter.extension.crud.model.query.PageQuery;
import top.charles7c.continew.starter.extension.crud.model.resp.PageResp;
import top.charles7c.continew.starter.extension.crud.util.ValidateGroup;
import top.charles7c.continew.starter.web.model.R;

import java.util.List;

/**
 * AI会话管理 API
 *
 * @author weiran
 * @since 2024/03/11 18:36
 */
@Tag(name = "用户AI会话管理 API")
@RestController
@RequiredArgsConstructor
@CrudRequestMapping(value = "/custom/item")
public class ItemCustomController {
    private final ItemService baseService;

    @Operation(summary = "分页查询列表", description = "分页查询列表")
    @ResponseBody
    @GetMapping("/list")
    public R<PageResp<ItemResp>> page(ItemQuery query, @Validated PageQuery pageQuery) {
        return R.ok(this.baseService.page(query, pageQuery));
    }

    @Operation(summary = "新增数据", description = "新增数据")
    @ResponseBody
    @PostMapping("/add")
    public R<ItemReq> add(@Validated({ValidateGroup.Crud.Add.class}) @RequestBody ItemReq req) {
        String itemId = IdUtil.fastSimpleUUID();
        req.setCreateUser(LoginHelper.getUserId());
        req.setItemId(itemId);
        this.baseService.add(req);
        return R.ok("新增成功", req);
    }

    @Operation(summary = "修改数据", description = "修改数据")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @ResponseBody
    @PutMapping({"/{id}"})
    public R<Void> update(@Validated({ValidateGroup.Crud.Update.class}) @RequestBody ItemReq req,
                          @PathVariable Long id) {
        req.setCreateUser(LoginHelper.getUserId());
        this.baseService.update(req, id);
        return R.ok("修改成功");
    }

    @Operation(summary = "删除数据", description = "删除数据")
    @Parameter(name = "ids", description = "ID 列表", example = "1,2", in = ParameterIn.PATH)
    @ResponseBody
    @DeleteMapping({"/{ids}"})
    public R<Void> delete(@PathVariable List<Long> ids) {
        this.baseService.delete(ids);
        return R.ok("删除成功");
    }

}