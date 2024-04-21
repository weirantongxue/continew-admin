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

import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

/**
 * 课程信息查询条件
 *
 * @author weiran
 * @since 2024/04/07 18:32
 */
@Data
@Schema(description = "课程信息查询条件")
public class CoursesInfoQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 课程教程id
     */
    @Schema(description = "课程教程id")
    @Query(type = QueryType.EQ)
    private Long coursesId;

    /**
     * 课程名称
     */
    @Schema(description = "课程名称")
    @Query(type = QueryType.EQ)
    private String name;

    /**
     * 课程类型 1:视频 ,2:文件
     */
    @Schema(description = "课程类型 1:视频 ,2:文件")
    @Query(type = QueryType.EQ)
    private Integer type;

    /**
     * 文件类型 1:mp4 ,2:doc
     */
    @Schema(description = "文件类型 1:mp4 ,2:doc")
    @Query(type = QueryType.EQ)
    private Integer fileType;

    /**
     * 状态（1：启用；2：禁用）
     */
    @Schema(description = "状态（1：启用；2：禁用）")
    @Query(type = QueryType.EQ)
    private Integer status;

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