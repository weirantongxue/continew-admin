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

package top.continew.admin.front.model.resp;

import java.io.Serial;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 账户扣费记录信息
 *
 * @author weiran
 * @since 2024/05/07 17:11
 */
@Data
@Schema(description = "账户扣费记录信息")
public class DeptAccountLogResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 部门账户信息
     */
    @Schema(description = "部门账户信息")
    private Long deptId;

    /**
     * 消息id
     */
    @Schema(description = "消息id")
    private String messageId;

    /**
     * 模型类型,1:大语言模型,2:文生图
     */
    @Schema(description = "模型类型,1:大语言模型,2:文生图")
    private Integer modelType;

    /**
     * 消耗代币
     */
    @Schema(description = "消耗代币")
    private Integer token;
}