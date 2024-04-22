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

package top.continew.admin.front.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by WeiRan on 2024.03.14 17:41
 */
@Data
public class DrawReq implements Serializable {

    @Schema(description = "提示词")
    @NotBlank(message = "提示词不能为空")
    private String prompt;

    @Schema(description = "批次大小")
    @Max(value = 4, message = "批次大小最大值为 {value}")
    private Integer batchSize = 4;

    @Schema(description = "图片宽度")
    @NotBlank(message = "图片宽度不能为空")
    @Max(value = 800, message = "图片宽度大小最大值为 {value}")
    private Integer width = 512;

    @Schema(description = "图片高度")
    @NotBlank(message = "图片高度不能为空")
    @Max(value = 800, message = "图片高度大小最大值为 {value}")
    private Integer height = 512;

    @Schema(description = "模型风格ID")
    private Integer modelStyleId = 919;

    @Schema(description = "模型id")
    @NotBlank(message = "模型id不能为空")
    private Long modelId;

}
