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

package top.charles7c.continew.admin.webapi.ai.consumer;

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
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.extension.crud.model.resp.PageResp;
import top.continew.starter.extension.crud.util.ValidateGroup;
import top.continew.starter.web.model.R;

import java.util.List;

/**
 * AI会话管理 API
 *
 * @author weiran
 * @since 2024/03/11 18:36
 */
@Tag(name = "用户AI会话管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ai/item/consumer")
public class ConItemController {

    private final ItemService itemService;

    @Operation(summary = "用户会话列表查询", description = "用户会话列表查询")
    @ResponseBody
    @GetMapping("/itemList")
    public R<PageResp<ItemResp>> page(ItemQuery query, @Validated PageQuery pageQuery) {
        query.setCreateUser(LoginHelper.getUserId());
        pageQuery.setSort(new String[] {"create_time", "desc"});
        return R.ok(this.itemService.page(query, pageQuery));
    }

    @Operation(summary = "用户新增会话", description = "用户新增会话")
    @ResponseBody
    @PostMapping("/addItem")
    public R<Long> add(@Validated({ValidateGroup.Crud.Add.class}) @RequestBody ItemReq req) {
        return R.ok("新增成功", this.itemService.add(req));
    }

    @Operation(summary = "用户修改会话名称", description = "用户修改会话名称")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @ResponseBody
    @PutMapping({"/updateItem/{id}"})
    public R<Void> update(@Validated({ValidateGroup.Crud.Update.class}) @RequestBody ItemReq req,
                          @PathVariable Long id) {
        this.itemService.update(req, id);
        return R.ok("修改成功");
    }

    @Operation(summary = "用户删除会话", description = "用户删除会话")
    @Parameter(name = "ids", description = "ID 列表", example = "1,2", in = ParameterIn.PATH)
    @ResponseBody
    @DeleteMapping({"/deleteItem/{ids}"})
    public R<Void> delete(@PathVariable List<Long> ids) {
        this.itemService.delete(ids);
        return R.ok("删除成功");
    }
}