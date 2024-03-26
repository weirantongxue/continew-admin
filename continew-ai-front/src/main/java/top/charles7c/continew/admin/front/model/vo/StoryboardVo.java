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

package top.charles7c.continew.admin.front.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.charles7c.continew.admin.front.model.resp.StoryboardFieResp;
import top.charles7c.continew.admin.front.model.resp.StoryboardResp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WeiRan on 2024.03.26 20:18
 */
@Data
public class StoryboardVo implements Serializable {
    @Schema(description = "分镜名信息")
    private List<StoryboardFieResp> storyboardFieRespList;
    @Schema(description = "分镜列信息")
    private List<StoryboardResp> storyboardList;

}
