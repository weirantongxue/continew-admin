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

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.charles7c.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改对话消息信息
 *
 * @author weiran
 * @since 2024/03/10 23:15
 */
@Data
@Schema(description = "创建或修改对话消息信息")
public class ChatMessageReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 会话id
     */
    @Schema(description = "会话id")
    @Length(max = 255, message = "会话id长度不能超过 {max} 个字符")
    private String itemId;

    /**
     * 消息id
     */
    @Schema(description = "消息id")
    @NotBlank(message = "消息id不能为空")
    @Length(max = 255, message = "消息id长度不能超过 {max} 个字符")
    private String messageId;

    /**
     * 是否采纳,0:未点击.1:采纳.2,未采纳
     */
    @Schema(description = "是否采纳,0:未点击.1:采纳.2,未采纳")
    @NotNull(message = "是否采纳,0:未点击.1:采纳.2,未采纳不能为空")
    private Boolean adopt;

    /**
     * 输入词块数
     */
    @Schema(description = "输入词块数")
    @NotNull(message = "输入词块数不能为空")
    private Integer inputTokens;

    /**
     * 输出词块数
     */
    @Schema(description = "输出词块数")
    @NotNull(message = "输出词块数不能为空")
    private Integer outputTokens;

    /**
     * 总词块数
     */
    @Schema(description = "总词块数")
    @NotNull(message = "总词块数不能为空")
    private Integer totalTokens;

    /**
     * 总请求耗时
     */
    @Schema(description = "总请求耗时")
    @NotNull(message = "总请求耗时不能为空")
    private Long responseTime;

    /**
     * 聊天耗时
     */
    @Schema(description = "聊天耗时")
    @NotNull(message = "聊天耗时不能为空")
    private Long chatResponseTime;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @NotNull(message = "创建人不能为空")
    private Long createUser;
}