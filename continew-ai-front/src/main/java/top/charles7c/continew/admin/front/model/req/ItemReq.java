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

package top.charles7c.continew.admin.front.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.continew.starter.extension.crud.model.req.BaseReq;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 创建或修改AI会话信息
 *
 * @author weiran
 * @since 2024/03/11 18:36
 */
@Data
@Schema(description = "创建或修改AI会话信息")
public class ItemReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 会话名称
     */
    @Schema(description = "会话名称")
    @NotBlank(message = "会话名称不能为空")
    @Length(max = 255, message = "会话名称长度不能超过 {max} 个字符")
    private String name;

    /**
     * 脚本id
     */
    @Schema(description = "脚本id")
    @NotBlank(message = "脚本id不能为空")
    private String modelScriptId;

    /**
     * 消息数
     */
    @Schema(description = "消息数")
    private Integer number;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private Long createUser;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}