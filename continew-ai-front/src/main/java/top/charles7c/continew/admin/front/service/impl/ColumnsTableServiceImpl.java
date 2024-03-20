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

package top.charles7c.continew.admin.front.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.charles7c.continew.admin.front.mapper.ColumnContentMapper;
import top.charles7c.continew.admin.front.mapper.ColumnRowMapper;
import top.charles7c.continew.admin.front.mapper.ColumnsMapper;
import top.charles7c.continew.admin.front.mapper.ColumnsProjectMapper;
import top.charles7c.continew.admin.front.service.ColumnsTableService;

/**
 * Created by WeiRan on 2024.03.20 19:16
 */
@Service
@RequiredArgsConstructor
public class ColumnsTableServiceImpl implements ColumnsTableService {
    private final ColumnsProjectMapper columnsProjectMapper;
    private final ColumnsMapper columnsMapper;
    private final ColumnRowMapper columnRowMapper;
    private final ColumnContentMapper columnContentMapper;


    @Override
    public Object selectTable(long projectId) {
        return null;
    }

    @Override
    public Object addRows(long projectId) {
        return null;
    }

    @Override
    public Object addColumn(long projectId) {
        return null;
    }
}
