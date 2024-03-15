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

package top.charles7c.continew.admin.front.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.charles7c.continew.admin.front.model.entity.DrawImgDO;
import top.charles7c.continew.admin.front.model.entity.DrawTaskDO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WeiRan on 2024.03.15 12:01
 */
@Schema(description = "绘图任务信息")
@Data
public class DrawResp implements Serializable {
    @Schema(description = "绘图任务")
    private DrawTaskDO drawTaskDO;
    @Schema(description = "绘图内容")
    private List<DrawImgDO> drawImgDOList;
}
