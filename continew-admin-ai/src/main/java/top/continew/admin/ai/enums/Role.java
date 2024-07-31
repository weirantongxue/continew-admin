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

package top.continew.admin.ai.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by WeiRan on 2024.07.04 12:01
 */
@Getter
@AllArgsConstructor
public enum Role {
    SYSTEM("system"), USER("user"), ASSISTANT("assistant"), FUNCTION("function");

    private final String name;
}
