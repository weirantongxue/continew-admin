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

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.charles7c.continew.admin.front.mapper.StoryboardFieMapper;
import top.charles7c.continew.admin.front.mapper.StoryboardMapper;
import top.charles7c.continew.admin.front.model.entity.StoryboardDO;
import top.charles7c.continew.admin.front.model.entity.StoryboardFieDO;
import top.charles7c.continew.admin.front.model.req.StoryboardSortReq;
import top.charles7c.continew.admin.front.model.resp.StoryboardFieResp;
import top.charles7c.continew.admin.front.model.resp.StoryboardResp;
import top.charles7c.continew.admin.front.model.vo.StoryboardVo;
import top.charles7c.continew.admin.front.service.StoryboardService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WeiRan on 2024.03.26 20:12
 */

/**
 * 分镜列业务实现
 *
 * @author weiran
 * @since 2024/03/26 20:11
 */
@Service
@RequiredArgsConstructor
public class StoryboardServiceImpl implements StoryboardService {
    private final StoryboardMapper storyboardMapper;
    private final StoryboardFieMapper storyboardFieMapper;

    @Override
    public StoryboardVo list(Long projectId) {
        StoryboardVo storyboardVo = new StoryboardVo();
        List<StoryboardDO> storyboardDOList = storyboardMapper.lambdaQuery()
            .eq(StoryboardDO::getProjectId, projectId)
            .orderByAsc(StoryboardDO::getShot)
            .list();
        List<StoryboardResp> storyboardList = BeanUtil.copyToList(storyboardDOList, StoryboardResp.class);

        List<StoryboardFieDO> storyboardFieDOList = storyboardFieMapper.lambdaQuery()
            .eq(StoryboardFieDO::getProjectId, projectId)
            .list();
        List<StoryboardFieResp> storyboardFieList = BeanUtil.copyToList(storyboardFieDOList, StoryboardFieResp.class);

        storyboardVo.setStoryboardList(storyboardList);
        storyboardVo.setStoryboardFieRespList(storyboardFieList);
        return storyboardVo;
    }

    @Override
    public boolean add(Long projectId, int rows) {
        Integer shot = storyboardMapper.sortMax(projectId);
        List<StoryboardDO> storyboardDOList = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            shot = shot + 1;
            StoryboardDO storyboardDO = new StoryboardDO();
            storyboardDO.setShot(shot);
            storyboardDO.setProjectId(projectId);
            storyboardDOList.add(storyboardDO);
        }
        return storyboardMapper.insertBatch(storyboardDOList);
    }

    @Override
    public int updateTable(StoryboardResp storyboardResp) {
        StoryboardDO storyboardDO = BeanUtil.copyProperties(storyboardResp, StoryboardDO.class);
        return storyboardMapper.updateById(storyboardDO);
    }

    @Override
    public int deleteTable(Long id) {
        return storyboardMapper.deleteById(id);
    }

    @Override
    public void storyboardSort(List<StoryboardSortReq> columnsSortReq) {
        List<StoryboardDO> storyboardDOList = BeanUtil.copyToList(columnsSortReq, StoryboardDO.class);
        storyboardMapper.updateBatchById(storyboardDOList);
    }
}
