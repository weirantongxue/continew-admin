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

/**
 * 创建或修改模型预设脚本信息
 *
 * @author weiran
 * @since 2024/03/21 14:48
 */
@Data
@Schema(description = "创建或修改模型预设脚本信息")
public class ModelScriptReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 脚本名称
     */
    @Schema(description = "脚本名称")
    @NotBlank(message = "脚本名称不能为空")
    @Length(max = 255, message = "脚本名称长度不能超过 {max} 个字符")
    private String name;

    /**
     * 模型名称
     */
    @Schema(description = "模型名称")
    @NotNull(message = "模型名称不能为空")
    private Long modelId;

    /**
     * 预设内容
     */
    @Schema(description = "预设内容")
    @NotBlank(message = "预设内容不能为空")
    @Length(max = 2048, message = "预设内容长度不能超过 {max} 个字符")
    private String prompt;

    /**
     * 封面
     */
    @Schema(description = "封面")
    @Length(max = 255, message = "封面长度不能超过 {max} 个字符")
    private String coverUrl;

    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    @Length(max = 255, message = "组件路径长度不能超过 {max} 个字符")
    private String component;

    /**
     * 简介
     */
    @Schema(description = "简介")
    @Length(max = 255, message = "描述长度不能超过 {max} 个字符")
    private String description;

    /**
     * 排序值
     */
    @Schema(description = "排序值")
    @NotNull(message = "排序值不能为空")
    private Integer sort;

    /**
     * 状态（1：启用；2：禁用）
     */
    @Schema(description = "状态（1：启用；2：禁用）")
    @NotNull(message = "状态（1：启用；2：禁用）不能为空")
    private Integer status;
}