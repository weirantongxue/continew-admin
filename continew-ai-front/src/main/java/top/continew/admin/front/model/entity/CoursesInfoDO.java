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
 * 课程信息实体
 *
 * @author weiran
 * @since 2024/04/07 18:32
 */
@Data
@TableName("lb_courses_info")
public class CoursesInfoDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 课程教程id
     */
    private Long coursesId;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 课程类型 1:视频 ,2:文件
     */
    private Integer type;

    /**
     * 文件类型 1:mp4 ,2:doc
     */
    private Integer fileType;

    /**
     * 文件地址
     */
    private String fileUrl;

    /**
     * 文件id
     */
    private String fileId;

    /**
     * 封面url
     */
    private String coverUrl;

    /**
     * 时长
     */
    private String duration;

    /**
     * token
     */
    private String token;

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