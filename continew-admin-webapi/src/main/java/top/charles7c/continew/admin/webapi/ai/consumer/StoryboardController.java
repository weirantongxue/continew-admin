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
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.charles7c.continew.admin.front.model.req.StoryboardSortReq;
import top.charles7c.continew.admin.front.model.resp.StoryboardResp;
import top.charles7c.continew.admin.front.model.vo.StoryboardVo;
import top.charles7c.continew.admin.front.service.StoryboardService;
import top.charles7c.continew.starter.web.model.R;

import java.util.List;

/**
 * Created by WeiRan on 2024.03.26 20:14
 */
@Tag(name = "分镜Table")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ai/consumer/storyboards")
@Slf4j
public class StoryboardController {
    private final StoryboardService storyboardSortService;

    @Operation(summary = "列表查询", description = "列表查询")
    @GetMapping("/list")
    public R<StoryboardVo> list(Long projectId) {
        return R.ok(storyboardSortService.list(projectId));
    }

    @Operation(summary = "新增镜头", description = "新建镜头")
    @GetMapping("/add")
    public R<Object> add(Long projectId, int rows) {
        return R.ok(storyboardSortService.add(projectId, rows));
    }

    @Operation(summary = "修改内容", description = "修改内容")
    @GetMapping("/updateTable")
    public R<Object> add(StoryboardResp storyboardResp) {
        return R.ok(storyboardSortService.updateTable(storyboardResp));
    }

    @Operation(summary = "删除行", description = "删除行")
    @GetMapping("/deleteTable")
    public R<Object> deleteTable(Long id) {
        return R.ok(storyboardSortService.deleteTable(id));
    }

    @Operation(summary = "上下排序", description = "上下排序")
    @PostMapping("/storyboardSort")
    public R<Object> storyboardSort(@RequestBody List<StoryboardSortReq> columnsSortReq) {
        storyboardSortService.storyboardSort(columnsSortReq);
        return R.ok();
    }

}
