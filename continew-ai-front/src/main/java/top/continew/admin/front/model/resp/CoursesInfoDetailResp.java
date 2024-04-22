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

package top.continew.admin.front.model.resp;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

import java.io.Serial;

/**
 * 课程信息详情信息
 *
 * @author weiran
 * @since 2024/04/07 18:32
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "课程信息详情信息")
public class CoursesInfoDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 课程教程id
     */
    @Schema(description = "课程教程id")
    @ExcelProperty(value = "课程教程id")
    private Long coursesId;

    /**
     * 课程名称
     */
    @Schema(description = "课程名称")
    @ExcelProperty(value = "课程名称")
    private String name;

    /**
     * 课程类型 1:视频 ,2:文件
     */
    @Schema(description = "课程类型 1:视频 ,2:文件")
    @ExcelProperty(value = "课程类型 1:视频 ,2:文件")
    private Integer type;

    /**
     * 文件类型 1:mp4 ,2:doc
     */
    @Schema(description = "文件类型 1:mp4 ,2:doc")
    @ExcelProperty(value = "文件类型 1:mp4 ,2:doc")
    private Integer fileType;

    /**
     * 文件地址
     */
    @Schema(description = "文件地址")
    @ExcelProperty(value = "文件地址")
    private String fileUrl;

    /**
     * 文件id
     */
    @Schema(description = "文件id")
    @ExcelProperty(value = "文件id")
    private String fileId;

    /**
     * 封面url
     */
    @Schema(description = "封面url")
    @ExcelProperty(value = "封面url")
    private String coverUrl;

    /**
     * 时长
     */
    @Schema(description = "时长")
    @ExcelProperty(value = "时长")
    private String duration;

    /**
     * token
     */
    @Schema(description = "token")
    @ExcelProperty(value = "token")
    private String token;

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