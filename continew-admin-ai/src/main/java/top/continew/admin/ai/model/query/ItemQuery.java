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

package top.continew.admin.ai.model.query;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

/**
 * AI会话查询条件
 *
 * @author weiran
 * @since 2024/08/04 23:37
 */
@Data
@Schema(description = "AI会话查询条件")
public class ItemQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 会话名称
     */
    @Schema(description = "会话名称")
    @Query(type = QueryType.EQ)
    private String name;

    /**
     * 指令id
     */
    @Schema(description = "指令id")
    @Query(type = QueryType.EQ)
    private Long commandId;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @Query(type = QueryType.BETWEEN)
    private List<LocalDateTime> createTime;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @Query(type = QueryType.EQ)
    private Long createUser;
}