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
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.dreamlu.mica.core.result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.charles7c.continew.admin.front.service.ColumnsTableService;

/**
 * Created by WeiRan on 2024.03.20 19:14
 */
@Tag(name = "分镜列表")
@RestController
@RequestMapping("/ai/columns/table")
@RequiredArgsConstructor
public class ColumnsTableController {
    private final ColumnsTableService columnsTableService;

    @Operation(summary = "通过项目id查询表格", description = "通过项目id查询表格")
    @GetMapping("/selectTable")
    public R<Object> selectTable(long projectId) {
        return R.success(columnsTableService.selectTable(projectId));
    }


    @Operation(summary = "添加行", description = "添加行")
    @GetMapping("/addRows")
    public R<Object> addRows(long projectId) {
        return R.success(columnsTableService.addRows(projectId));
    }

    @Operation(summary = "添加列", description = "添加列")
    @GetMapping("/addColumn")
    public R<Object> addColumn(long projectId) {
        return R.success(columnsTableService.addColumn(projectId));
    }


}
