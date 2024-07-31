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

package top.continew.admin.ai.context;

import top.continew.admin.ai.strategy.ModelStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.starter.core.exception.BusinessException;

import java.util.Map;
import java.util.Optional;

/**
 * Created by WeiRan on 2021.12.10 15:11
 */
@Component
@AllArgsConstructor
public class ModelContext {

    private final Map<String, ModelStrategy> modelStrategyMap;

    public ModelStrategy handlerInstance(String type) {
        return Optional.of(modelStrategyMap.get(type)).orElseThrow(() -> new BusinessException("不支持此类型"));
    }

}
