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

package top.continew.admin.front.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.continew.starter.extension.crud.model.entity.BaseDO;

import java.io.Serial;

/**
 * 课程教程实体
 *
 * @author weiran
 * @since 2024/04/07 18:29
 */
@Data
@TableName("lb_courses")
public class CoursesDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 课程教程名称
     */
    private String name;

    /**
     * 文件id
     */
    private String fileId;

    /**
     * 时长
     */
    private String duration;

    /**
     * 描述
     */
    private String description;

    /**
     * 封面url
     */
    private String coverUrl;

    /**
     * 总节数
     */
    private Integer total;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 状态（1：启用；2：禁用）
     */
    private Integer status;

    /**
     * 是否删除: [0=否, 1=是]
     */
    private Integer isDeleted;
}