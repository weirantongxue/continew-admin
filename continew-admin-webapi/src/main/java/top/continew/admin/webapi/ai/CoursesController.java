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
import top.continew.admin.front.model.query.CoursesQuery;
import top.continew.admin.front.model.req.CoursesReq;
import top.continew.admin.front.model.resp.CoursesDetailResp;
import top.continew.admin.front.model.resp.CoursesResp;
import top.continew.admin.front.service.CoursesService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.starter.extension.crud.enums.Api;
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.web.model.R;

import java.util.List;

/**
 * 课程教程管理 API
 *
 * @author weiran
 * @since 2024/04/07 18:29
 */
@Tag(name = "课程教程管理 API")
@RestController
@CrudRequestMapping(value = "/ai/courses", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class CoursesController extends BaseController<CoursesService, CoursesResp, CoursesDetailResp, CoursesQuery, CoursesReq> {

    @Operation(summary = "课程教程管理列表", description = "课程教程管理列表")
    @ResponseBody
    @GetMapping("/coursesList")
    public R<List<CoursesResp>> coursesList(CoursesQuery coursesQuery, @Validated PageQuery pageQuery) {
        pageQuery.setSort(new String[] {"sort,asc"});
        return R.ok(baseService.list(coursesQuery, pageQuery));
    }

    @Operation(summary = "同步课程信息", description = "同步课程信息")
    @ResponseBody
    @GetMapping("/syncCourses")
    public R<Object> syncCourses(int id) {
        baseService.syncCourses(id);
        return R.ok();
    }

}