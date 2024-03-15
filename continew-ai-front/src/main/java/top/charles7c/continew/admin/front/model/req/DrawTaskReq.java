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

import java.io.Serial;
import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.charles7c.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改绘图任务信息
 *
 * @author weiran
 * @since 2024/03/15 11:49
 */
@Data
@Schema(description = "创建或修改绘图任务信息")
public class DrawTaskReq extends BaseReq {

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
     * 问题
     */
    @Schema(description = "问题")
    @NotBlank(message = "问题不能为空")
    @Length(max = 512, message = "问题长度不能超过 {max} 个字符")
    private String prompt;

    /**
     * 拼接图
     */
    @Schema(description = "拼接图")
    @Length(max = 512, message = "拼接图长度不能超过 {max} 个字符")
    private String mosaicImg;

    /**
     * 传递id
     */
    @Schema(description = "传递id")
    @NotBlank(message = "传递id不能为空")
    @Length(max = 255, message = "传递id长度不能超过 {max} 个字符")
    private String nonce;

    /**
     * 任务状态success
     */
    @Schema(description = "任务状态success")
    @NotBlank(message = "任务状态success不能为空")
    @Length(max = 32, message = "任务状态success长度不能超过 {max} 个字符")
    private String state;

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