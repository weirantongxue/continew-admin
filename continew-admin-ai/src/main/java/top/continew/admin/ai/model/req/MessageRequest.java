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

package top.continew.admin.ai.model.req;

import com.unfbx.chatgpt.entity.chat.Message;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Schema(description = "模型对话请求参数")
public class MessageRequest implements Serializable {

    @Schema(description = "请求渠道", example = "1")
    private Integer channel;

    @Schema(description = "会话ID")
    @NotBlank(message = "会话ID不能为空")
    private String itemId;

    @Schema(description = "模型")
    @NotBlank(message = "模型不能为空")
    private String model;

    @Schema(description = "对话信息")
    private List<Message> messages;

    private boolean stream = true;

}
