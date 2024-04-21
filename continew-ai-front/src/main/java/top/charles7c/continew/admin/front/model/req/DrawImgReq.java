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

package top.charles7c.continew.admin.front.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.continew.starter.extension.crud.model.req.BaseReq;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 创建或修改绘图素材信息
 *
 * @author weiran
 * @since 2024/03/15 11:43
 */
@Data
@Schema(description = "创建或修改绘图素材信息")
public class DrawImgReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    @Schema(description = "任务id")
    @NotBlank(message = "任务id不能为空")
    @Length(max = 255, message = "任务id长度不能超过 {max} 个字符")
    private String taskId;

    /**
     * 图片地址
     */
    @Schema(description = "图片地址")
    @NotBlank(message = "图片地址不能为空")
    @Length(max = 255, message = "图片地址长度不能超过 {max} 个字符")
    private String imageUrl;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @NotNull(message = "创建人不能为空")
    private Long createUser;
}