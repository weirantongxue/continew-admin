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
import net.dreamlu.mica.core.result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.charles7c.continew.admin.front.model.query.ModelScriptQuery;
import top.charles7c.continew.admin.front.model.req.ModelScriptReq;
import top.charles7c.continew.admin.front.model.resp.ModelScriptDetailResp;
import top.charles7c.continew.admin.front.model.resp.ModelScriptResp;
import top.charles7c.continew.admin.front.model.vo.ModelScriptVo;
import top.charles7c.continew.admin.front.service.ModelScriptService;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.starter.extension.crud.enums.Api;

import java.util.List;

/**
 * 模型预设脚本管理 API
 *
 * @author weiran
 * @since 2024/03/21 14:48
 */
@Tag(name = "模型预设脚本管理 API")
@RestController
@CrudRequestMapping(value = "/ai/modelScript", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class ModelScriptController extends BaseController<ModelScriptService, ModelScriptResp, ModelScriptDetailResp, ModelScriptQuery, ModelScriptReq> {

    @Operation(summary = "查询脚本列表", description = "查询脚本列表")
    @GetMapping("/selectModelScript")
    public R<List<ModelScriptVo>> selectTable() {
        List<ModelScriptVo> modelScriptVoList = this.baseService.selectModelScript();
        return R.success(modelScriptVoList);
    }

}