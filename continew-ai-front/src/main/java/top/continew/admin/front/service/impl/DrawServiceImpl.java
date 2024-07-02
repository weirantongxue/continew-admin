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
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.result.R;
import org.dromara.x.file.storage.core.FileInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.continew.admin.common.converter.FileToMultipartFileConverter;
import top.continew.admin.common.model.dto.LoginUser;
import top.continew.admin.common.util.ApiTokenUtils;
import top.continew.admin.common.util.helper.LoginHelper;
import top.continew.admin.front.mapper.DrawImgMapper;
import top.continew.admin.front.mapper.DrawTaskMapper;
import top.continew.admin.front.model.entity.DrawImgDO;
import top.continew.admin.front.model.entity.DrawTaskDO;
import top.continew.admin.front.model.req.DrawCallbackReq;
import top.continew.admin.front.model.req.DrawReq;
import top.continew.admin.front.model.resp.DrawImgDetailResp;
import top.continew.admin.front.model.resp.DrawResp;
import top.continew.admin.front.model.resp.DrawTaskResp;
import top.continew.admin.front.model.resp.ModelDetailResp;
import top.continew.admin.front.model.vo.DeptAccountVo;
import top.continew.admin.front.model.vo.DrawTaskVo;
import top.continew.admin.front.model.vo.HistoricalImagesVo;
import top.continew.admin.front.service.DeptAccountService;
import top.continew.admin.front.service.DrawService;
import top.continew.admin.front.service.ModelService;
import top.continew.admin.system.service.FileService;
import top.continew.starter.core.exception.BadRequestException;
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.extension.crud.model.resp.PageResp;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WeiRan on 2024.03.14 17:54
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DrawServiceImpl implements DrawService {
    private final DrawTaskMapper drawTaskMapper;

    private final DrawImgMapper drawImgMapper;

    private final ModelService modelService;

    private final FileService fileService;

    private final DeptAccountService deptAccountService;

    @Override
    public R<DrawTaskVo> createDrawTask(DrawReq drawReq) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        DeptAccountVo deptAccountVo = deptAccountService.selectBalance(loginUser.getDeptId());
        if (deptAccountVo.getBalanceToken() <= 0) {
            throw new BadRequestException("余额不足");
        }
        ModelDetailResp modelResp = modelService.get(drawReq.getModelId());
        if (modelResp == null) {
            throw new BadRequestException("模型不存在");
        }

        if ("chuzhanAi".equals(modelResp.getName())) {
            return chuzhanAi(drawReq, modelResp);
        }
        if ("cogview".equals(modelResp.getName())) {
            return cogview(drawReq, modelResp, loginUser.getDeptId(), loginUser.getId());
        }
        return R.fail("未匹配到对应模型,请联系管理员.");
    }

    private R<DrawTaskVo> cogview(DrawReq drawReq, ModelDetailResp modelResp, Long deptId, Long userId) {
        DrawTaskVo drawTaskVo = new DrawTaskVo();
        String authToken = ApiTokenUtils.generateClientToken("9258a4b118cd7545ea2389bfe07334fc.St00V5LEAYBr7F0b");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", modelResp.getName());
        jsonObject.put("prompt", drawReq.getPrompt());

        HttpRequest request = HttpRequest.post(modelResp.getUrl())
            .header("Authorization", authToken)
            .body(JSONObject.toJSONString(jsonObject));
        String result = request.execute().body();
        log.info("请求文生图返回结果:{}", JSONObject.toJSONString(result));
        JSONObject res = JSONObject.parseObject(result);
        if (res.containsKey("error")) {
            JSONObject error = res.getJSONObject("error");
            return R.fail(error.getString("message"));
        }
        JSONArray jsonArray = res.getJSONArray("data");
        String imgUrl = JSONObject.parseObject(jsonArray.get(0).toString()).getString("url");
        String taskId = IdUtil.fastSimpleUUID();
        if (StrUtil.isNotBlank(imgUrl)) {
            FileInfo fileInfo = new FileInfo();
            MultipartFile multipartFile = null;
            try {
                //下载图片转换成MultipartFile
                multipartFile = FileToMultipartFileConverter.convert(imgUrl);
                //上传到sso
                fileInfo = fileService.upload(multipartFile);

            } catch (IOException e) {
                log.error("MultipartFile 转换失败");
                throw new BadRequestException("MultipartFile 转换失败");
            }

            //创建任务
            DrawTaskDO drawTaskDO = new DrawTaskDO();
            drawTaskDO.setTaskId(taskId);
            drawTaskDO.setPrompt(drawReq.getPrompt());
            drawTaskDO.setNonce(taskId);
            drawTaskDO.setState("success");
            drawTaskMapper.insert(drawTaskDO);

            DrawImgDO drawImgDO = new DrawImgDO();
            drawImgDO.setTaskId(taskId);
            drawImgDO.setImageUrl(fileInfo.getUrl());
            drawImgDO.setCreateUser(LoginHelper.getUserId());
            drawImgDO.setUpdateUser(LoginHelper.getUserId());
            drawImgDO.setCreateTime(LocalDateTime.now());
            drawImgDO.setUpdateTime(LocalDateTime.now());
            drawImgMapper.insert(drawImgDO);

            drawTaskVo.setTaskId(taskId);
            drawTaskVo.setUrl(fileInfo.getUrl());
            drawTaskVo.setResType("sync");
            //扣减余额
            deptAccountService.deductBalance(deptId, 3, 2, userId, taskId);
            return R.success(drawTaskVo);
        }

        return R.success(drawTaskVo);
    }

    private R<DrawTaskVo> chuzhanAi(DrawReq drawReq, ModelDetailResp modelResp) {
        DrawTaskVo drawTaskVo = new DrawTaskVo();
        String nonce = IdUtil.fastSimpleUUID();
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(drawReq));
        jsonObject.put("callback", "http://101.201.33.35:8000/api/ai/draw/drawCallback");
        jsonObject.put("nonce", nonce);
        HttpRequest request = HttpRequest.post("https://ai.huashi6.com/aiapi/v1/draw")
            .header("Auth-Token", "lHOYOgNDXKssyTQTAqu0DyXrdMpPwx6z")
            .body(jsonObject.toJSONString());
        String result = request.execute().body();
        log.info("请求文生图返回结果:{}", JSONObject.toJSONString(result));
        JSONObject res = JSONObject.parseObject(result);
        if (res.containsKey("code") && 0 == res.getInteger("code")) {
            String paintingSign = res.getJSONObject("data").getString("paintingSign");
            if (StrUtil.isNotBlank(paintingSign)) {
                DrawTaskDO drawTaskDO = new DrawTaskDO();
                drawTaskDO.setTaskId(paintingSign);
                drawTaskDO.setPrompt(drawReq.getPrompt());
                drawTaskDO.setNonce(nonce);
                drawTaskMapper.insert(drawTaskDO);
                drawTaskVo.setTaskId(paintingSign);
                drawTaskVo.setResType("async");
                return R.success(drawTaskVo);
            }
            return R.fail("模型服务异常请联系管理员");
        }
        return R.fail("模型服务异常请联系管理员");
    }

    @Override
    public R<Object> checkDrawTask(String taskId) {
        boolean state = false;
        DrawTaskDO drawTaskDO = drawTaskMapper.lambdaQuery().eq(DrawTaskDO::getTaskId, taskId).one();
        if (null != drawTaskDO) {
            if ("success".equals(drawTaskDO.getState())) {
                state = true;
            }
        }
        return R.success(state);
    }

    @Override
    public R<DrawResp> drawTask(String taskId) {
        DrawResp drawResp = new DrawResp();
        DrawTaskDO drawTaskDO = drawTaskMapper.lambdaQuery().eq(DrawTaskDO::getTaskId, taskId).one();
        List<DrawImgDO> drawImgDOList = drawImgMapper.lambdaQuery().eq(DrawImgDO::getTaskId, taskId).list();
        drawResp.setDrawTaskDO(drawTaskDO);
        drawResp.setDrawImgDOList(drawImgDOList);
        return R.success(drawResp);
    }

    @Override
    public void drawCallback(DrawCallbackReq drawCallbackReq) {
        DrawTaskDO drawTask = drawTaskMapper.lambdaQuery().eq(DrawTaskDO::getTaskId, drawCallbackReq.getTaskId()).one();
        String taskId = drawCallbackReq.getTaskId();
        List<DrawImgDO> drawImgDOList = new ArrayList<>();
        drawCallbackReq.getImages().forEach(img -> {
            String imgUrl = img.getImageUrl();
            FileInfo fileInfo = new FileInfo();
            MultipartFile multipartFile = null;
            try {
                //下载图片转换成MultipartFile
                multipartFile = FileToMultipartFileConverter.convert(imgUrl);
                //上传到sso
                fileInfo = fileService.upload(multipartFile);

            } catch (IOException e) {
                log.error("MultipartFile 转换失败");
                throw new BadRequestException("MultipartFile 转换失败");
            }
            DrawImgDO drawImgDO = new DrawImgDO();
            drawImgDO.setTaskId(taskId);
            drawImgDO.setImageUrl(fileInfo.getUrl());
            drawImgDO.setCreateUser(drawTask.getCreateUser());
            drawImgDO.setUpdateUser(drawTask.getCreateUser());
            drawImgDO.setCreateTime(LocalDateTime.now());
            drawImgDO.setUpdateTime(LocalDateTime.now());
            drawImgDOList.add(drawImgDO);
        });
        drawImgMapper.insertBatch(drawImgDOList);
        drawTaskMapper.lambdaUpdate()
            .eq(DrawTaskDO::getTaskId, taskId)
            .set(DrawTaskDO::getState, drawCallbackReq.getState())
            .set(DrawTaskDO::getMosaicImg, drawCallbackReq.getImgUrl())
            .update();
    }

    @Override
    public R<PageResp<HistoricalImagesVo>> historicalImages(PageQuery pageQuery) {
        List<HistoricalImagesVo> historicalImagesVoList = new ArrayList<>();
        LambdaQueryWrapper<DrawTaskDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DrawTaskDO::getState, "success");
        queryWrapper.eq(DrawTaskDO::getCreateUser, LoginHelper.getUserId());
        queryWrapper.orderByDesc(DrawTaskDO::getCreateTime);

        IPage<DrawTaskDO> page = drawTaskMapper.selectPage(pageQuery.toPage(), queryWrapper);
        List<DrawTaskDO> recordList = page.getRecords();
        recordList.forEach(drawTaskDO -> {
            HistoricalImagesVo historicalImagesVo = new HistoricalImagesVo();
            List<DrawImgDO> drawImgDOList = drawImgMapper.lambdaQuery()
                .eq(DrawImgDO::getTaskId, drawTaskDO.getTaskId())
                .list();
            DrawTaskResp drawTaskResp = BeanUtil.copyProperties(drawTaskDO, DrawTaskResp.class);
            List<DrawImgDetailResp> drawImgDetailRespList = BeanUtil.copyToList(drawImgDOList, DrawImgDetailResp.class);

            historicalImagesVo.setDrawTaskResp(drawTaskResp);
            historicalImagesVo.setHistoricalImages(drawImgDetailRespList);
            historicalImagesVoList.add(historicalImagesVo);
        });
        Page<HistoricalImagesVo> page1 = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        page1.setRecords(historicalImagesVoList);
        PageResp<HistoricalImagesVo> pageResp = PageResp.build(page1);
        return R.success(pageResp);
    }

}
