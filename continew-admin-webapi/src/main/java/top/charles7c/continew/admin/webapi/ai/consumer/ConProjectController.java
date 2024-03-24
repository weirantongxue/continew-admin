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

import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.dreamlu.mica.core.result.R;
import org.springframework.web.bind.annotation.*;
import top.charles7c.continew.admin.front.service.ColumnsProjectService;

import java.util.List;

/**
 * Created by WeiRan on 2024.03.20 18:31
 */
@Tag(name = "分镜项目")
@RestController
@RequestMapping("/ai/columns")
@RequiredArgsConstructor
public class ConProjectController {
    private final ColumnsProjectService columnsProjectService;

    @Operation(summary = "查询列表", description = "查询列表")
    @GetMapping("/list")
    public R<Object> list() {
        return R.success(columnsProjectService.list());
    }

    @Operation(summary = "新增项目", description = "新增项目")
    @GetMapping("/add")
    public R<Object> add(String name) {
        if (StrUtil.isBlank(name)) {
            return R.fail("参数错误");
        }
        return R.success(columnsProjectService.add(name));
    }

    @Operation(summary = "修改名称", description = "修改名称")
    @GetMapping("/update")
    public R<Object> update(Long id, String name) {
        if (null == id || null == name) {
            return R.fail("参数错误");
        }
        columnsProjectService.update(id, name);
        return R.success();
    }

    @Operation(summary = "删除", description = "删除")
    @ResponseBody
    @GetMapping("/delete/{ids}")
    public R<Object> delete(@PathVariable List<Long> ids) {
        if (null == ids) {
            return R.fail("ids不能为空");
        }
        columnsProjectService.delete(ids);
        return R.success();
    }
}
