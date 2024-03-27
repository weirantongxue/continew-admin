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

import java.io.Serial;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 模型预设脚本信息
 *
 * @author weiran
 * @since 2024/03/21 14:48
 */
@Data
@Schema(description = "模型预设脚本信息")
public class ModelScriptResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 脚本名称
     */
    @Schema(description = "脚本名称")
    private String name;

    /**
     * 模型名称
     */
    @Schema(description = "模型名称")
    private Long modelId;

    /**
     * 预设内容
     */
    @Schema(description = "预设内容")
    private String prompt;

    /**
     * 封面
     */
    @Schema(description = "封面")
    private String coverUrl;

    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    private String component;

    /**
     * 简介
     */
    @Schema(description = ",简介")
    private String description;

    /**
     * 排序值
     */
    @Schema(description = "排序值")
    private Integer sort;

    /**
     * 状态（1：启用；2：禁用）
     */
    @Schema(description = "状态（1：启用；2：禁用）")
    private Integer status;
}