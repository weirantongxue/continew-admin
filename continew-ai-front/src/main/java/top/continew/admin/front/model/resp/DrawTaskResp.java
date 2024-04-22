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

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.starter.extension.crud.model.resp.BaseResp;

import java.io.Serial;

/**
 * 绘图任务信息
 *
 * @author weiran
 * @since 2024/03/15 11:49
 */
@Data
@Schema(description = "绘图任务信息")
public class DrawTaskResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    @Schema(description = "任务id")
    private String taskId;

    /**
     * 问题
     */
    @Schema(description = "问题")
    private String prompt;

    /**
     * 拼接图
     */
    @Schema(description = "拼接图")
    private String mosaicImg;

    /**
     * 传递id
     */
    @Schema(description = "传递id")
    private String nonce;

    /**
     * 任务状态success
     */
    @Schema(description = "任务状态success")
    private String state;
}