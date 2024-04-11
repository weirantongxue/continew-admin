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
import top.charles7c.continew.admin.common.model.dto.LoginUser;
import top.charles7c.continew.admin.common.util.helper.LoginHelper;
import top.charles7c.continew.admin.front.model.vo.DeptAccountVo;
import top.charles7c.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.charles7c.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.charles7c.continew.starter.extension.crud.controller.BaseController;
import top.charles7c.continew.admin.front.model.query.DeptAccountQuery;
import top.charles7c.continew.admin.front.model.req.DeptAccountReq;
import top.charles7c.continew.admin.front.model.resp.DeptAccountDetailResp;
import top.charles7c.continew.admin.front.model.resp.DeptAccountResp;
import top.charles7c.continew.admin.front.service.DeptAccountService;
import top.charles7c.continew.starter.log.core.annotation.Log;
import top.charles7c.continew.starter.web.model.R;

/**
 * 部门账户管理 API
 *
 * @author weiran
 * @since 2024/04/01 18:42
 */
@Tag(name = "部门账户管理 API")
@RestController
@CrudRequestMapping(value = "/ai/deptAccount", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class DeptAccountController extends BaseController<DeptAccountService, DeptAccountResp, DeptAccountDetailResp, DeptAccountQuery, DeptAccountReq> {

    @Log(ignore = true)
    @Operation(summary = "查询部门账户余额", description = "查询部门账户余额")
    @ResponseBody
    @GetMapping("/selectBalance")
    public R<DeptAccountVo> selectBalance() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        return R.ok(baseService.selectBalance(loginUser.getDeptId()));
    }

    @Log(ignore = true)
    @Operation(summary = "余额扣减", description = "余额扣减")
    @ResponseBody
    @GetMapping("/deductBalance")
    public R<Object> deductBalance() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        baseService.deductBalance(loginUser.getDeptId(), 1);
        return R.ok();
    }

}