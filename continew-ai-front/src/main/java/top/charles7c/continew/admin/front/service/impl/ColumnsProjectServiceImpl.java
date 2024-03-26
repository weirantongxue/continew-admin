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
import top.charles7c.continew.admin.front.model.entity.StoryboardFieDO;
import top.charles7c.continew.admin.front.service.ColumnsProjectService;
import top.charles7c.continew.admin.front.service.StoryboardFieService;
import top.charles7c.continew.admin.system.model.query.DictItemQuery;
import top.charles7c.continew.admin.system.model.resp.DictItemResp;
import top.charles7c.continew.admin.system.service.DictItemService;
import top.charles7c.continew.starter.extension.crud.model.query.SortQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WeiRan on 2024.03.20 18:27
 */
@Service
@RequiredArgsConstructor
public class ColumnsProjectServiceImpl implements ColumnsProjectService {
    private final ColumnsProjectMapper columnsProjectMapper;
    private final StoryboardFieService storyboardFieService;
    private final DictItemService dictItemService;

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
        //初始化标题
        List<StoryboardFieDO> storyboardFieDOList = columnsDOList(columnsProjectDO.getId());
        storyboardFieService.insertBatch(storyboardFieDOList);
        return id;
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

    private List<StoryboardFieDO> columnsDOList(long projectId) {
        List<StoryboardFieDO> storyboardFieDOList = new ArrayList<>();
        String[] sort = {"sort", "asc"};
        SortQuery sortQuery = new SortQuery();
        sortQuery.setSort(sort);
        DictItemQuery dictItemQuery = new DictItemQuery();
        dictItemQuery.setDictId(560936357447446910L);
        List<DictItemResp> dictItemRespList = dictItemService.list(dictItemQuery, sortQuery);
        for (DictItemResp dictItemResp : dictItemRespList) {
            StoryboardFieDO storyboardFieDO = new StoryboardFieDO();
            storyboardFieDO.setProjectId(projectId);
            storyboardFieDO.setFieId(dictItemResp.getValue());
            storyboardFieDO.setName(dictItemResp.getLabel());
            storyboardFieDOList.add(storyboardFieDO);
        }
        return storyboardFieDOList;
    }

}
