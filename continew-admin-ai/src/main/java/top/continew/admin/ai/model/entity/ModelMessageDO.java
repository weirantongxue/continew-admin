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

package top.continew.admin.ai.model.entity;

import java.io.Serial;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 对话消息实体
 *
 * @author weiran
 * @since 2024/08/04 23:38
 */
@Data
@TableName("lb_model_message")
public class ModelMessageDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 会话id
     */
    private String itemId;

    /**
     * 消息id
     */
    private String messageId;

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 提问
     */
    private String prompt;

    /**
     * 回答
     */
    private String output;

    /**
     * 模型名称
     */
    private String model;

    /**
     * 是否采纳,0:未点击.1:采纳.2,未采纳
     */
    private Boolean adopt;

    /**
     * 输入词块数
     */
    private Long promptTokens;

    /**
     * 输出词块数
     */
    private Long completionTokens;

    /**
     * 总词块数
     */
    private Long totalTokens;

    /**
     * 总请求耗时
     */
    private Long responseTime;

    /**
     * 聊天耗时
     */
    private Long chatResponseTime;

    /**
     * 是否删除: [0=否, 1=是]
     */
    private Integer isDeleted;
}