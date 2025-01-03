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

package top.continew.admin.ai.model.chat.resp;

import lombok.Data;
import top.continew.admin.ai.model.chat.ChatChoice;
import top.continew.admin.ai.model.chat.Usage;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WeiRan on 2025.01.02 18:31
 */
@Data
public class ChatCompletionResponse implements Serializable {
    private String id;
    private String object;
    private Long created;
    private String model;
    private String systemFingerprint;
    private List<ChatChoice> choices;
    private Usage usage;
}
