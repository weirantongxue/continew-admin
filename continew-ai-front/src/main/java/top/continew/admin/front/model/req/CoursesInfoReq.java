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
 * 创建或修改课程信息信息
 *
 * @author weiran
 * @since 2024/04/07 18:32
 */
@Data
@Schema(description = "创建或修改课程信息信息")
public class CoursesInfoReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 课程教程id
     */
    @Schema(description = "课程教程id")
    @NotNull(message = "课程教程id不能为空")
    private Long coursesId;

    /**
     * 课程名称
     */
    @Schema(description = "课程名称")
    @NotBlank(message = "课程名称不能为空")
    @Length(max = 255, message = "课程名称长度不能超过 {max} 个字符")
    private String name;

    /**
     * 课程类型 1:视频 ,2:文件
     */
    @Schema(description = "课程类型 1:视频 ,2:文件")
    private Integer type;

    /**
     * 文件类型 1:mp4 ,2:doc
     */
    @Schema(description = "文件类型 1:mp4 ,2:doc")
    private Integer fileType;

    /**
     * 文件地址
     */
    @Schema(description = "文件地址")
    private String fileUrl;

    /**
     * 文件id
     */
    @Schema(description = "文件id")
    @Length(max = 255, message = "文件id长度不能超过 {max} 个字符")
    private String fileId;

    /**
     * 封面url
     */
    @Schema(description = "封面url")
    @Length(max = 255, message = "封面url长度不能超过 {max} 个字符")
    private String coverUrl;

    /**
     * 时长
     */
    @Schema(description = "时长")
    @Length(max = 255, message = "时长长度不能超过 {max} 个字符")
    private String duration;

    /**
     * token
     */
    @Schema(description = "token")
    @Length(max = 255, message = "时长长度不能超过 {max} 个字符")
    private String token;

    /**
     * 排序值
     */
    @Schema(description = "排序值")
    private Integer sort;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private Long createUser;
}