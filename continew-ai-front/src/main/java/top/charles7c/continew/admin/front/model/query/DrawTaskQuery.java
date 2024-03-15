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

package top.charles7c.continew.admin.front.model.query;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.data.core.annotation.Query;
import top.charles7c.continew.starter.data.core.enums.QueryType;

/**
 * 绘图任务查询条件
 *
 * @author weiran
 * @since 2024/03/15 11:49
 */
@Data
@Schema(description = "绘图任务查询条件")
public class DrawTaskQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    @Schema(description = "任务id")
    @Query(type = QueryType.EQ)
    private String taskId;

    /**
     * 问题
     */
    @Schema(description = "问题")
    @Query(type = QueryType.EQ)
    private String prompt;

    /**
     * 传递id
     */
    @Schema(description = "传递id")
    @Query(type = QueryType.EQ)
    private String nonce;

    /**
     * 任务状态success
     */
    @Schema(description = "任务状态success")
    @Query(type = QueryType.EQ)
    private String state;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @Query(type = QueryType.EQ)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @Query(type = QueryType.EQ)
    private Long createUser;
}