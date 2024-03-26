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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.charles7c.continew.admin.common.util.helper.LoginHelper;
import top.charles7c.continew.admin.front.mapper.ColumnsProjectMapper;
import top.charles7c.continew.admin.front.model.entity.ColumnsProjectDO;
import top.charles7c.continew.admin.front.service.ColumnsProjectService;

import java.util.List;

/**
 * Created by WeiRan on 2024.03.20 18:27
 */
@Service
@RequiredArgsConstructor
public class ColumnsProjectServiceImpl implements ColumnsProjectService {
    private final ColumnsProjectMapper columnsProjectMapper;

    @Override
    public List<ColumnsProjectDO> list() {
        List<ColumnsProjectDO> list = columnsProjectMapper.selectList(new LambdaQueryWrapper<ColumnsProjectDO>()
                .eq(ColumnsProjectDO::getCreateUser, LoginHelper.getUserId())
                .orderByDesc(ColumnsProjectDO::getCreateTime));
        return list;
    }

    @Override
    public int add(String name) {
        ColumnsProjectDO columnsProjectDO = new ColumnsProjectDO();
        columnsProjectDO.setName(name);
        int id = columnsProjectMapper.insert(columnsProjectDO);
        return id ;
    }

    @Override
    public boolean update(Long id, String name) {
        return columnsProjectMapper.lambdaUpdate()
                .eq(ColumnsProjectDO::getId, id)
                .set(ColumnsProjectDO::getName, name)
                .update();
    }

    @Override
    public int delete(List<Long> ids) {
        return columnsProjectMapper.deleteBatchIds(ids);
    }

}
