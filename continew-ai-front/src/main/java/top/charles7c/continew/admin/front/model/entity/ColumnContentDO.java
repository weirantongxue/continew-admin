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

package top.charles7c.continew.admin.front.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 分镜列行对应标题对应内容实体
 *
 * @author weiran
 * @since 2024/03/20 18:20
 */
@Data
@TableName("lb_column_content")
public class ColumnContentDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId
    private Long id;

    /**
     * 行id
     */
    @Schema(description = "行id")
    private String rowId;

    /**
     * 标题id
     */
    @Schema(description = "标题id")
    private Long columnsId;

    /**
     * 内容
     */
    @Schema(description = "内容")
    private String content;
}