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

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.charles7c.continew.admin.common.util.ParameterUtils;
import top.charles7c.continew.admin.front.model.vo.CategoryVo;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.charles7c.continew.admin.front.mapper.CoursesMapper;
import top.charles7c.continew.admin.front.model.entity.CoursesDO;
import top.charles7c.continew.admin.front.model.query.CoursesQuery;
import top.charles7c.continew.admin.front.model.req.CoursesReq;
import top.charles7c.continew.admin.front.model.resp.CoursesDetailResp;
import top.charles7c.continew.admin.front.model.resp.CoursesResp;
import top.charles7c.continew.admin.front.service.CoursesService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 课程教程业务实现
 *
 * @author weiran
 * @since 2024/04/07 18:29
 */
@Service
@RequiredArgsConstructor
public class CoursesServiceImpl extends BaseServiceImpl<CoursesMapper, CoursesDO, CoursesResp, CoursesDetailResp, CoursesQuery, CoursesReq> implements CoursesService {
    private final CoursesMapper coursesMapper;

    /**
     * 同步课程信息
     *
     * @return
     */
    @Override
    public void syncCourses(int id) {
        //        Map<String, Object> map = new HashMap<>();
        //        String timestamp = String.valueOf(DateUtil.current());
        //        map.put("partner_id", 37441272);
        //        map.put("timestamp", timestamp);
        //        map.put("sign", SignGeneratorUtils.getBaiJaYunSign(map));
        //根据分类id获取课程
        String re = HttpUtil.post("https://e37441272.at.baijiayun.com/openapi/video/getCategoryList", ParameterUtils
            .categoryListAssembly());
        JSONObject jsonObject = JSONObject.parseObject(re);
        if (jsonObject.containsKey("code") && 0 == jsonObject.getInteger("code")) {
            List<CategoryVo> categoryVoList = JSONObject.parseArray(jsonObject.getJSONObject("data")
                .getString("list"), CategoryVo.class);
            List<CategoryVo> filteredList = categoryVoList.stream()
                .filter(categoryVo -> categoryVo.getId() == id)
                .collect(Collectors.toList());
            if (ObjectUtil.isNotNull(filteredList)) {
                List<CoursesDO> coursesDOList = new ArrayList<>();
                for (int i = 0; i < filteredList.get(0).getChildren().size(); i++) {
                    CoursesDO coursesDO = new CoursesDO();
                    coursesDO.setName(filteredList.get(0).getChildren().get(i).getName());
                    coursesDO.setFileId(String.valueOf(filteredList.get(0).getChildren().get(i).getId()));
                    coursesDO.setTotal(filteredList.get(0).getChildren().get(i).getVideo_count());
                    coursesDO.setSort(i);
                    coursesDOList.add(coursesDO);
                }
                coursesMapper.insertBatch(coursesDOList);
            }
        }
    }

    @Override
    public List<CoursesDO> coursesList(Integer id) {
        LambdaQueryWrapper<CoursesDO> queryWrapper = new LambdaQueryWrapper<>();
        if (null != id) {
            queryWrapper.eq(CoursesDO::getFileId, id);
        }
        return coursesMapper.selectList(queryWrapper);
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("partner_id", 37441272);
        // map.put("category_id", 34976);
        map.put("sign", "78fc18d5c0c129032526dc53ca09afae");
        map.put("timestamp", "1712488878792");
        //根据分类id获取课程
        String re = HttpUtil.post("https://e37441272.at.baijiayun.com/openapi/video/getCategoryList", map);
        JSONObject jsonObject = JSONObject.parseObject(re);
        if (jsonObject.containsKey("code") && 0 == jsonObject.getInteger("code")) {
            List<CategoryVo> categoryVoList = JSONObject.parseArray(jsonObject.getJSONObject("data")
                .getString("list"), CategoryVo.class);
            categoryVoList.forEach(categoryVo -> {
                System.out.println(JSONObject.toJSONString(categoryVo));
                System.out.println("-------------------------");
            });

        }

    }

}