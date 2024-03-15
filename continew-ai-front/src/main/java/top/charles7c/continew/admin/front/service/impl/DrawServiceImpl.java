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

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.result.R;
import org.dromara.x.file.storage.core.FileInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.charles7c.continew.admin.common.converter.FileToMultipartFileConverter;
import top.charles7c.continew.admin.front.mapper.DrawImgMapper;
import top.charles7c.continew.admin.front.mapper.DrawTaskMapper;
import top.charles7c.continew.admin.front.model.entity.DrawImgDO;
import top.charles7c.continew.admin.front.model.entity.DrawTaskDO;
import top.charles7c.continew.admin.front.model.req.DrawCallbackReq;
import top.charles7c.continew.admin.front.model.req.DrawReq;
import top.charles7c.continew.admin.front.model.resp.DrawResp;
import top.charles7c.continew.admin.front.service.DrawService;
import top.charles7c.continew.starter.core.exception.BadRequestException;
import top.charles7c.continew.admin.system.service.FileService;

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

    private final FileService fileService;

    @Override
    public R<Object> createDrawTask(DrawReq drawReq) {
        String nonce = IdUtil.fastSimpleUUID();
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(drawReq));
        jsonObject.put("callback", "http://101.201.33.35:8000/ai/draw/drawCallback");
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
                return R.success();
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
}
