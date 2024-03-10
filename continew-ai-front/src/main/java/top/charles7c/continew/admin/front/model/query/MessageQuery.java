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

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.data.core.annotation.Query;
import top.charles7c.continew.starter.data.core.enums.QueryType;

/**
 * 对话消息查询条件
 *
 * @author weiran
 * @since 2024/03/10 23:15
 */
@Data
@Schema(description = "对话消息查询条件")
public class MessageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 会话id
     */
    @Schema(description = "会话id")
    @Query(type = QueryType.EQ)
    private String itemId;

    /**
     * 消息id
     */
    @Schema(description = "消息id")
    @Query(type = QueryType.EQ)
    private String messageId;

    /**
     * 提问
     */
    @Schema(description = "提问")
    @Query(type = QueryType.LIKE)
    private String question;

    /**
     * 是否采纳,0:未点击.1:采纳.2,未采纳
     */
    @Schema(description = "是否采纳,0:未点击.1:采纳.2,未采纳")
    @Query(type = QueryType.EQ)
    private Boolean adopt;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @Query(type = QueryType.EQ)
    private Long createUser;
}