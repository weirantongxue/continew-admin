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

package top.charles7c.continew.admin.front.model.resp;

import java.io.Serial;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.charles7c.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * 课程教程详情信息
 *
 * @author weiran
 * @since 2024/04/07 18:29
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "课程教程详情信息")
public class CoursesDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 课程教程名称
     */
    @Schema(description = "课程教程名称")
    @ExcelProperty(value = "课程教程名称")
    private String name;

    /**
     * 文件id
     */
    @Schema(description = "文件id")
    @ExcelProperty(value = "文件id")
    private String fileId;

    /**
     * 时长
     */
    @Schema(description = "时长")
    @ExcelProperty(value = "时长")
    private String duration;

    /**
     * 描述
     */
    @Schema(description = "描述")
    @ExcelProperty(value = "描述")
    private String description;

    /**
     * 封面url
     */
    @Schema(description = "封面url")
    @ExcelProperty(value = "封面url")
    private String coverUrl;

    /**
     * 总节数
     */
    @Schema(description = "总节数")
    @ExcelProperty(value = "总节数")
    private Integer total;

    /**
     * 排序值
     */
    @Schema(description = "排序值")
    @ExcelProperty(value = "排序值")
    private Integer sort;

    /**
     * 状态（1：启用；2：禁用）
     */
    @Schema(description = "状态（1：启用；2：禁用）")
    @ExcelProperty(value = "状态（1：启用；2：禁用）")
    private Integer status;

    /**
     * 是否删除: [0=否, 1=是]
     */
    @Schema(description = "是否删除: [0=否, 1=是]")
    @ExcelProperty(value = "是否删除: [0=否, 1=是]")
    private Integer isDeleted;
}