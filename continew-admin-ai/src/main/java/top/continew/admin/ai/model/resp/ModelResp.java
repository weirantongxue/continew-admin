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

package top.continew.admin.ai.model.resp;

import java.io.Serial;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * AI模型信息
 *
 * @author weiran
 * @since 2024/08/04 23:35
 */
@Data
@Schema(description = "AI模型信息")
public class ModelResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    @Schema(description = "模型名称")
    private String name;

    /**
     * 展示名称
     */
    @Schema(description = "展示名称")
    private String displayName;

    /**
     * 模型类型,1:大语言模型,2:文生图
     */
    @Schema(description = "模型类型,1:大语言模型,2:文生图")
    private Integer type;

    /**
     * 模型图标
     */
    @Schema(description = "模型图标")
    private String coverUrl;

    /**
     * 模型地址
     */
    @Schema(description = "模型地址")
    private String url;

    /**
     * apikey
     */
    @Schema(description = "apikey")
    private String apiKey;

    /**
     * 回调地址
     */
    @Schema(description = "回调地址")
    private String callBackUrl;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String introduction;

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

    /**
     * 修改人
     */
    @Schema(description = "修改人")
    private Long updateUser;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    private LocalDateTime updateTime;
}