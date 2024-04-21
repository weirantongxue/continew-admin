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

import top.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 分镜列信息
 *
 * @author weiran
 * @since 2024/03/31 03:52
 */
@Data
@Schema(description = "分镜列信息")
public class StoryboardResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 场号
     */
    @Schema(description = "场号")
    private String sceneNumber;

    /**
     * 镜号
     */
    @Schema(description = "镜号")
    private Integer shot;

    /**
     * 画面
     */
    @Schema(description = "画面")
    private String picture;

    /**
     * 景别
     */
    @Schema(description = "景别")
    private String shotSize;

    /**
     * 时长（秒）
     */
    @Schema(description = "时长（秒）")
    private String duration;

    /**
     * 内容
     */
    @Schema(description = "内容")
    private String content;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 场景
     */
    @Schema(description = "场景")
    private String sceneAdvice;

    /**
     * 声音
     */
    @Schema(description = "声音")
    private String voice;

    /**
     * 摄影机角度
     */
    @Schema(description = "摄影机角度")
    private String cameraAngle;

    /**
     * 运镜
     */
    @Schema(description = "运镜")
    private String cameraMove;

    /**
     * 摄影机装备
     */
    @Schema(description = "摄影机装备")
    private String cameraEquipment;

    /**
     * 项目id
     */
    @Schema(description = "项目id")
    private Long projectId;

}