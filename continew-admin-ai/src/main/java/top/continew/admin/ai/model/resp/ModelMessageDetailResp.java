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

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * 对话消息详情信息
 *
 * @author weiran
 * @since 2024/08/04 23:38
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "对话消息详情信息")
public class ModelMessageDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 会话id
     */
    @Schema(description = "会话id")
    @ExcelProperty(value = "会话id")
    private String itemId;

    /**
     * 消息id
     */
    @Schema(description = "消息id")
    @ExcelProperty(value = "消息id")
    private String messageId;

    /**
     * 任务id
     */
    @Schema(description = "任务id")
    @ExcelProperty(value = "任务id")
    private String taskId;

    /**
     * 提问
     */
    @Schema(description = "提问")
    @ExcelProperty(value = "提问")
    private String prompt;

    /**
     * 回答
     */
    @Schema(description = "回答")
    @ExcelProperty(value = "回答")
    private String output;

    /**
     * 模型名称
     */
    @Schema(description = "模型名称")
    @ExcelProperty(value = "模型名称")
    private String model;

    /**
     * 是否采纳,0:未点击.1:采纳.2,未采纳
     */
    @Schema(description = "是否采纳,0:未点击.1:采纳.2,未采纳")
    @ExcelProperty(value = "是否采纳,0:未点击.1:采纳.2,未采纳")
    private Boolean adopt;

    /**
     * 输入词块数
     */
    @Schema(description = "输入词块数")
    @ExcelProperty(value = "输入词块数")
    private Long promptTokens;

    /**
     * 输出词块数
     */
    @Schema(description = "输出词块数")
    @ExcelProperty(value = "输出词块数")
    private Long completionTokens;

    /**
     * 总词块数
     */
    @Schema(description = "总词块数")
    @ExcelProperty(value = "总词块数")
    private Long totalTokens;

    /**
     * 总请求耗时
     */
    @Schema(description = "总请求耗时")
    @ExcelProperty(value = "总请求耗时")
    private Long responseTime;

    /**
     * 聊天耗时
     */
    @Schema(description = "聊天耗时")
    @ExcelProperty(value = "聊天耗时")
    private Long chatResponseTime;

    /**
     * 是否删除: [0=否, 1=是]
     */
    @Schema(description = "是否删除: [0=否, 1=是]")
    @ExcelProperty(value = "是否删除: [0=否, 1=是]")
    private Integer isDeleted;
}