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

package top.continew.admin.ai.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WeiRan on 2024.11.09 01:32
 */

@Data
@Builder
public class ChatCompletion implements Serializable {
    private String model;
    private List<Message> messages;
    private String request_id;
    private Boolean do_sample;
    private Boolean stream;
    private Float temperature;
    private Float top_p;
    private Integer max_tokens;
    private List<String> stop;
    private List<Tool> tools;
    private Object tool_choice; // It can be either String or Object, so we use Object type here.
    private String user_id;
}
