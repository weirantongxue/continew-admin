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

package top.continew.admin.front.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.front.mapper.ColumnContentMapper;
import top.continew.admin.front.mapper.ColumnRowMapper;
import top.continew.admin.front.mapper.ColumnsMapper;
import top.continew.admin.front.model.entity.ColumnContentDO;
import top.continew.admin.front.model.entity.ColumnRowDO;
import top.continew.admin.front.model.entity.ColumnsDO;
import top.continew.admin.front.model.req.ColumnsSortReq;
import top.continew.admin.front.model.resp.ColumnsRowResp;
import top.continew.admin.front.model.resp.ColumnsTableResp;
import top.continew.admin.front.service.ColumnsTableService;
import top.continew.admin.system.model.query.DictItemQuery;
import top.continew.admin.system.model.resp.DictItemResp;
import top.continew.admin.system.service.DictItemService;
import top.continew.starter.extension.crud.model.query.SortQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WeiRan on 2024.03.20 19:16
 */
@Service
@RequiredArgsConstructor
public class ColumnsTableServiceImpl implements ColumnsTableService {
    private final ColumnsMapper columnsMapper;
    private final ColumnRowMapper columnRowMapper;
    private final ColumnContentMapper columnContentMapper;
    private final DictItemService dictItemService;

    @Override
    public ColumnsTableResp selectTable(long projectId) {
        ColumnsTableResp columnsTableResp = new ColumnsTableResp();
        List<ColumnsRowResp> columnsRowRespList = new ArrayList<>();
        List<ColumnsDO> columnsDOList = columnsMapper.lambdaQuery()
            .eq(ColumnsDO::getProjectId, projectId)
            .orderByAsc(ColumnsDO::getSort)
            .list();
        columnsTableResp.setColumnsList(columnsDOList);
        List<ColumnRowDO> columnRowDOList = columnRowMapper.lambdaQuery()
            .eq(ColumnRowDO::getProjectId, projectId)
            .list();
        for (ColumnRowDO columnRowDO : columnRowDOList) {
            ColumnsRowResp columnsRowResp = new ColumnsRowResp();
            List<ColumnContentDO> columnContentDOList = columnContentMapper.lambdaQuery()
                .eq(ColumnContentDO::getRowId, columnRowDO.getRowId())
                .list();
            columnsRowResp.setColumnRow(columnRowDO);
            columnsRowResp.setColumnContentList(columnContentDOList);
            columnsRowRespList.add(columnsRowResp);
        }
        columnsTableResp.setColumnsRowRespList(columnsRowRespList);
        return columnsTableResp;
    }

    @Override
    public boolean addRows(long projectId, int rows) {
        if (columnsMapper.lambdaQuery().eq(ColumnsDO::getProjectId, projectId).count() <= 0) {
            List<ColumnsDO> columnsDOList = columnsDOList(projectId);
            //初始化添加内容
            columnsMapper.insertBatch(columnsDOList);
        }
        List<ColumnRowDO> columnRowDOList = new ArrayList<>();
        int sort = columnRowMapper.sortMax(projectId);
        for (int i = 0; i < rows; i++) {
            ColumnRowDO columnRowDO = new ColumnRowDO();
            columnRowDO.setRowId(IdUtil.fastSimpleUUID());
            columnRowDO.setSort(sort + i + 1);
            columnRowDO.setProjectId(projectId);
            columnRowDOList.add(columnRowDO);
        }
        return columnRowMapper.insertBatch(columnRowDOList);
    }

    @Override
    public int addColumn(long projectId, String title, int dataType) {
        ColumnsDO columnsDO = new ColumnsDO();
        columnsDO.setProjectId(projectId);
        columnsDO.setTitle(title);
        columnsDO.setDataType(dataType);
        columnsDO.setSort(columnsMapper.sortMax(projectId) + 1);
        return columnsMapper.insert(columnsDO);
    }

    @Override
    public int addContent(ColumnContentDO columnContentDO) {
        if (columnContentMapper.exists(new LambdaQueryWrapper<ColumnContentDO>()
            .eq(ColumnContentDO::getRowId, columnContentDO.getRowId())
            .eq(ColumnContentDO::getColumnsId, columnContentDO.getColumnsId()))) {
            columnContentMapper.lambdaUpdate()
                .eq(ColumnContentDO::getRowId, columnContentDO.getRowId())
                .eq(ColumnContentDO::getColumnsId, columnContentDO.getColumnsId())
                .set(ColumnContentDO::getContent, columnContentDO.getContent())
                .update();
            return 1;
        }
        return columnContentMapper.insert(columnContentDO);
    }

    @Override
    public void columnsSort(ColumnsSortReq columnsSortReq) {
        List<ColumnsDO> columnsDOList = BeanUtil.copyToList(columnsSortReq.getSortReqList(), ColumnsDO.class);
        columnsMapper.updateBatchById(columnsDOList);
    }

    @Override
    public void rowSort(ColumnsSortReq columnsSortReq) {
        List<ColumnRowDO> columnRowDOList = BeanUtil.copyToList(columnsSortReq.getSortReqList(), ColumnRowDO.class);
        columnRowMapper.updateBatchById(columnRowDOList);
    }

    private List<ColumnsDO> columnsDOList(long projectId) {
        List<ColumnsDO> columnsDOList = new ArrayList<>();
        String[] sort = {"sort", "asc"};
        SortQuery sortQuery = new SortQuery();
        sortQuery.setSort(sort);
        DictItemQuery dictItemQuery = new DictItemQuery();
        dictItemQuery.setDictId(560936357447446910L);
        List<DictItemResp> dictItemRespList = dictItemService.list(dictItemQuery, sortQuery);
        for (DictItemResp dictItemResp : dictItemRespList) {
            ColumnsDO columnsDO = new ColumnsDO();
            columnsDO.setProjectId(projectId);
            columnsDO.setTitle(dictItemResp.getLabel());
            columnsDO.setSort(dictItemResp.getSort());
            columnsDO.setDataType(Integer.valueOf(dictItemResp.getDescription()));
            columnsDOList.add(columnsDO);
        }
        return columnsDOList;
    }

}
