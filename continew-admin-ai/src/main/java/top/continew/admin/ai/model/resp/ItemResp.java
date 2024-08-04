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
 * AI会话信息
 *
 * @author weiran
 * @since 2024/08/04 23:37
 */
@Data
@Schema(description = "AI会话信息")
public class ItemResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 会话名称
     */
    @Schema(description = "会话名称")
    private String name;

    /**
     * 指令id
     */
    @Schema(description = "指令id")
    private Long commandId;

    /**
     * 消息数
     */
    @Schema(description = "消息数")
    private Integer number;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    @Schema(description = "修改人")
    private Long updateUser;
}