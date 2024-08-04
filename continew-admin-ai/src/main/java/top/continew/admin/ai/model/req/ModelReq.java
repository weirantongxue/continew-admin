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

package top.continew.admin.ai.model.req;

import java.io.Serial;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改AI模型信息
 *
 * @author weiran
 * @since 2024/08/04 23:35
 */
@Data
@Schema(description = "创建或修改AI模型信息")
public class ModelReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    @Schema(description = "模型名称")
    @NotBlank(message = "模型名称不能为空")
    @Length(max = 255, message = "模型名称长度不能超过 {max} 个字符")
    private String name;

    /**
     * 展示名称
     */
    @Schema(description = "展示名称")
    @NotBlank(message = "展示名称不能为空")
    @Length(max = 255, message = "展示名称长度不能超过 {max} 个字符")
    private String displayName;

    /**
     * 模型类型,1:大语言模型,2:文生图
     */
    @Schema(description = "模型类型,1:大语言模型,2:文生图")
    @NotNull(message = "模型类型,1:大语言模型,2:文生图不能为空")
    private Integer type;

    /**
     * 模型图标
     */
    @Schema(description = "模型图标")
    @Length(max = 255, message = "模型图标长度不能超过 {max} 个字符")
    private String coverUrl;

    /**
     * 模型地址
     */
    @Schema(description = "模型地址")
    @NotBlank(message = "模型地址不能为空")
    @Length(max = 255, message = "模型地址长度不能超过 {max} 个字符")
    private String url;

    /**
     * apikey
     */
    @Schema(description = "apikey")
    @NotBlank(message = "apikey不能为空")
    @Length(max = 255, message = "apikey长度不能超过 {max} 个字符")
    private String apiKey;

    /**
     * 回调地址
     */
    @Schema(description = "回调地址")
    @Length(max = 255, message = "回调地址长度不能超过 {max} 个字符")
    private String callBackUrl;

    /**
     * 排序值
     */
    @Schema(description = "排序值")
    private Integer sort;

    /**
     * 状态（1：启用；2：禁用）
     */
    @Schema(description = "状态（1：启用；2：禁用）")
    @NotNull(message = "状态（1：启用；2：禁用）不能为空")
    private Integer status;
}