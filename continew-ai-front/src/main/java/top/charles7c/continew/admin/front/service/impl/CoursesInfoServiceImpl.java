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

import cn.hutool.core.date.DateUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.charles7c.continew.admin.common.util.ParameterUtils;
import top.charles7c.continew.admin.common.util.SignGeneratorUtils;
import top.charles7c.continew.admin.front.model.entity.CoursesDO;
import top.charles7c.continew.admin.front.service.CoursesService;
import top.charles7c.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.charles7c.continew.admin.front.mapper.CoursesInfoMapper;
import top.charles7c.continew.admin.front.model.entity.CoursesInfoDO;
import top.charles7c.continew.admin.front.model.query.CoursesInfoQuery;
import top.charles7c.continew.admin.front.model.req.CoursesInfoReq;
import top.charles7c.continew.admin.front.model.resp.CoursesInfoDetailResp;
import top.charles7c.continew.admin.front.model.resp.CoursesInfoResp;
import top.charles7c.continew.admin.front.service.CoursesInfoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程信息业务实现
 *
 * @author weiran
 * @since 2024/04/07 18:32
 */
@Service
@RequiredArgsConstructor
public class CoursesInfoServiceImpl extends BaseServiceImpl<CoursesInfoMapper, CoursesInfoDO, CoursesInfoResp, CoursesInfoDetailResp, CoursesInfoQuery, CoursesInfoReq> implements CoursesInfoService {
    private final CoursesInfoMapper coursesInfoMapper;
    private final CoursesService coursesService;


    @Override
    public void syncCoursesInfo() {
        List<CoursesDO> coursesDOList = coursesService.coursesInfoList();
        coursesDOList.forEach(courses -> {
            if (courses.getTotal()>0) {
                String timestamp = String.valueOf(DateUtil.current());
                //分页数据
               int pageCount= getPageCount(courses.getTotal(), 20);
                for (int i = 0; i <pageCount ; i++) {
                    //获取课程信息
                    ParameterUtils.categoryVideoAssembly(courses.getFileId(),i+1);
                }

            }
        });
    }

    public static void main(String[] args) {
        int pageCount = getPageCount(0, 20);
        System.out.println(pageCount);
    }

    public static int getPageCount(int totalCount, int pageSize) {
        return (totalCount - 1) / pageSize + 1;
    }
}