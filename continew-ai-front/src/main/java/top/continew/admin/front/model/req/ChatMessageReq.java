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

package top.continew.admin.front.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.continew.starter.extension.crud.model.req.BaseReq;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 创建或修改对话消息信息
 *
 * @author weiran
 * @since 2024/03/12 00:07
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
    @NotBlank(message = "会话id不能为空")
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
     * 任务id
     */
    @Schema(description = "任务id")
    @Length(max = 255, message = "任务id长度不能超过 {max} 个字符")
    private String taskId;

    /**
     * 提问
     */
    @Schema(description = "提问")
    private String question;

    /**
     * 回答
     */
    @Schema(description = "回答")
    private String answer;

    /**
     * 模型名称
     */
    @Schema(description = "模型名称")
    @Length(max = 255, message = "模型名称长度不能超过 {max} 个字符")
    private String model;

    /**
     * 是否采纳,0:未点击.1:采纳.2,未采纳
     */
    @Schema(description = "是否采纳,0:未点击.1:采纳.2,未采纳")
    private Boolean adopt;

    /**
     * ip信息
     */
    @Schema(description = "ip信息")
    @Length(max = 32, message = "ip信息长度不能超过 {max} 个字符")
    private String ip;

    /**
     * 输入词块数
     */
    @Schema(description = "输入词块数")
    private Long promptTokens;

    /**
     * 输出词块数
     */
    @Schema(description = "输出词块数")
    private Long completionTokens;

    /**
     * 总词块数
     */
    @Schema(description = "总词块数")
    private Long totalTokens;

    /**
     * 总请求耗时
     */
    @Schema(description = "总请求耗时")
    private Long responseTime;

    /**
     * 聊天耗时
     */
    @Schema(description = "聊天耗时")
    private Long chatResponseTime;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @NotNull(message = "创建人不能为空")
    private Long createUser;

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