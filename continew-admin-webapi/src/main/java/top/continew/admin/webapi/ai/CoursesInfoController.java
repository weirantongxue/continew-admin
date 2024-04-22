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
import top.continew.admin.front.model.query.CoursesInfoQuery;
import top.continew.admin.front.model.req.CoursesInfoReq;
import top.continew.admin.front.model.resp.CoursesInfoDetailResp;
import top.continew.admin.front.model.resp.CoursesInfoResp;
import top.continew.admin.front.service.CoursesInfoService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.starter.extension.crud.enums.Api;
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.web.model.R;

import java.util.List;

/**
 * 课程信息管理 API
 *
 * @author weiran
 * @since 2024/04/07 18:32
 */
@Tag(name = "课程信息管理 API")
@RestController
@CrudRequestMapping(value = "/ai/coursesInfo", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class CoursesInfoController extends BaseController<CoursesInfoService, CoursesInfoResp, CoursesInfoDetailResp, CoursesInfoQuery, CoursesInfoReq> {

    @Operation(summary = "课程信息管理列表", description = "课程信息管理列表")
    @ResponseBody
    @GetMapping("/coursesInfoList")
    public R<List<CoursesInfoResp>> coursesInfoList(CoursesInfoQuery coursesInfoQuery, @Validated PageQuery pageQuery) {
        pageQuery.setSort(new String[] {"sort,asc"});
        return R.ok(baseService.list(coursesInfoQuery, pageQuery));
    }

    @Operation(summary = "同步课程内容", description = "同步课程内容")
    @ResponseBody
    @GetMapping("/syncCoursesInfo")
    public R<Object> syncCoursesInfo(Integer id) {
        baseService.syncCoursesInfo(id);
        return R.ok();
    }
}