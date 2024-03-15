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

import cn.dev33.satoken.annotation.SaIgnore;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.result.R;
import org.springframework.web.bind.annotation.*;
import top.charles7c.continew.admin.front.model.req.DrawCallbackReq;
import top.charles7c.continew.admin.front.model.req.DrawReq;
import top.charles7c.continew.admin.front.model.resp.DrawResp;
import top.charles7c.continew.admin.front.service.DrawService;
import top.charles7c.continew.starter.log.core.annotation.Log;

/**
 * Created by WeiRan on 2024.03.14 17:37
 */
@Tag(name = "绘画 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ai/draw")
@Slf4j
public class DrawController {
    private final DrawService drawService;

    @Operation(summary = "文生图", description = "文生图")
    @PostMapping("/createDrawTask")
    public R<Object> createDrawTask(@RequestBody DrawReq drawReq) {
        return drawService.createDrawTask(drawReq);
    }

    @Operation(summary = "通过任务id查询任务状态", description = "通过任务id查询任务状态")
    @GetMapping("/checkDrawTask")
    public R<Object> checkDrawTask(String taskId) {
        return drawService.checkDrawTask(taskId);
    }

    @Operation(summary = "通过任务id查询生成内容", description = "通过任务id查询生成内容")
    @GetMapping("/drawTask")
    public R<DrawResp> drawTask(String taskId) {
        return drawService.drawTask(taskId);
    }

    @SaIgnore
    @Log(ignore = true)
    @Operation(summary = "文生图回调参数", description = "文生图")
    @PostMapping("/drawCallback")
    public void drawCallback(@RequestBody DrawCallbackReq drawCallbackReq) {
        log.info("文生图回调参数:{}", JSONObject.toJSONString(drawCallbackReq));
        drawService.drawCallback(drawCallbackReq);
    }

}
