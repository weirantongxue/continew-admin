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

package top.charles7c.continew.admin.front.model.validate;

import com.unfbx.chatgpt.entity.chat.Message;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Schema(description = "对话参数")
public class ChatMessageRequestValidate implements Serializable {

    //    @NotBlank(message = "channel参数缺失")
    //    @Schema(description = "请求渠道")
    //    private Integer channel;

    @NotBlank(message = "itemId参数缺失")
    @Schema(description = "会话id")
    private String itemId;

    @NotBlank(message = "model参数缺失")
    @Schema(description = "模型")
    private String model;

    @NotBlank(message = "对话信息")
    @Schema(description = "对话信息")
    private List<Message> messages;

    private boolean stream = true;

}
