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

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.common.util.ParameterUtils;
import top.continew.admin.front.mapper.CoursesInfoMapper;
import top.continew.admin.front.model.entity.CoursesDO;
import top.continew.admin.front.model.entity.CoursesInfoDO;
import top.continew.admin.front.model.query.CoursesInfoQuery;
import top.continew.admin.front.model.req.CoursesInfoReq;
import top.continew.admin.front.model.resp.CoursesInfoDetailResp;
import top.continew.admin.front.model.resp.CoursesInfoResp;
import top.continew.admin.front.model.vo.CategoryVideo;
import top.continew.admin.front.service.CoursesInfoService;
import top.continew.admin.front.service.CoursesService;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.util.ArrayList;
import java.util.List;

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
    public void syncCoursesInfo(Integer id) {
        List<CoursesDO> coursesDOList = coursesService.coursesList(id);
        coursesDOList.forEach(courses -> {
            if (courses.getTotal() > 0) {
                //分页数据
                int pageCount = getPageCount(courses.getTotal(), 100);
                if (pageCount != 0) {
                    for (int i = 0; i < pageCount; i++) {
                        //获取课程信息
                        String re = HttpUtil
                            .post("https://e37441272.at.baijiayun.com/openapi/video/getCategoryVideo", ParameterUtils
                                .categoryVideoAssembly(courses.getFileId(), i + 1));
                        JSONObject jsonObject = JSONObject.parseObject(re);
                        if (jsonObject.containsKey("code") && 0 == jsonObject.getInteger("code")) {
                            List<CoursesInfoDO> coursesInfoDOList = new ArrayList<>();
                            List<CategoryVideo> categoryVideoList = JSONObject.parseArray(jsonObject
                                .getJSONObject("data")
                                .getString("list"), CategoryVideo.class);
                            if (CollectionUtil.isNotEmpty(categoryVideoList)) {
                                for (int i1 = 0; i1 < categoryVideoList.size(); i1++) {
                                    CoursesInfoDO coursesInfoDO = new CoursesInfoDO();
                                    coursesInfoDO.setCoursesId(courses.getId());
                                    coursesInfoDO.setName(categoryVideoList.get(i1).getName());
                                    coursesInfoDO.setFileId(String.valueOf(categoryVideoList.get(i1).getVideo_id()));
                                    coursesInfoDO.setCoverUrl(categoryVideoList.get(i1).getPreface_url());
                                    coursesInfoDO.setDuration(categoryVideoList.get(i1).getLength());
                                    coursesInfoDO.setType(1);
                                    coursesInfoDO.setSort(i1);
                                    String token = "";
                                    //获取token
                                    String res = HttpUtil
                                        .post("https://e37441272.at.baijiayun.com/openapi/video/getPlayerToken", ParameterUtils
                                            .playerTokenAssembly(categoryVideoList.get(i1).getVideo_id()));
                                    JSONObject jsonObject1 = JSONObject.parseObject(res);
                                    if (jsonObject1.containsKey("code") && 0 == jsonObject1.getInteger("code")) {
                                        token = jsonObject1.getJSONObject("data").getString("token");
                                    }
                                    coursesInfoDO.setToken(token);
                                    coursesInfoDO.setFileUrl("https://e37441272.at.baijiayun.com/web/video/player");
                                    coursesInfoDOList.add(coursesInfoDO);
                                }
                                coursesInfoMapper.insertBatch(coursesInfoDOList);
                            }
                        }

                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        int pageCount = getPageCount(0, 20);
        System.out.println(pageCount);
    }

    public static int getPageCount(int totalCount, int pageSize) {
        if (totalCount == 0) {
            return 0;
        }
        return (totalCount - 1) / pageSize + 1;
    }

}