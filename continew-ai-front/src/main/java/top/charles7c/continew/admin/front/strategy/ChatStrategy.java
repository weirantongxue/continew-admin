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

package top.charles7c.continew.admin.front.strategy;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import top.charles7c.continew.admin.front.model.validate.ChatMessageRequestValidate;

/**
 * Created by WeiRan on 2021.12.10 15:11
 */
public interface ChatStrategy {
    SseEmitter aiApi(ChatMessageRequestValidate messageCreateValidate);

}
