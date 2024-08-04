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
 * AI会话实体
 *
 * @author weiran
 * @since 2024/08/04 23:37
 */
@Data
@TableName("lb_item")
public class ItemDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 会话名称
     */
    private String name;

    /**
     * 指令id
     */
    private Long commandId;

    /**
     * 消息数
     */
    private Integer number;

    /**
     * 是否删除: [0=否, 1=是]
     */
    private Integer isDeleted;
}