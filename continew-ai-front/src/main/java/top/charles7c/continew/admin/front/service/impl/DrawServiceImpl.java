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
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.result.R;
import org.springframework.stereotype.Component;
import top.charles7c.continew.admin.front.model.req.DrawCallbackReq;
import top.charles7c.continew.admin.front.model.req.DrawReq;
import top.charles7c.continew.admin.front.service.DrawService;

/**
 * Created by WeiRan on 2024.03.14 17:54
 */
@Component
@Slf4j
public class DrawServiceImpl implements DrawService {
    @Override
    public R<Object> draw(DrawReq drawReq) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(drawReq));
        jsonObject.put("callback", "http://101.201.33.35:8000/ai/draw/drawCallback");
        jsonObject.put("nonce", IdUtil.fastSimpleUUID());
        HttpRequest request = HttpRequest.post("https://ai.huashi6.com/aiapi/v1/draw")
            .header("Auth-Token", "lHOYOgNDXKssyTQTAqu0DyXrdMpPwx6z")
            .body(jsonObject.toJSONString());
        String result = request.execute().body();
        log.info("请求文生图返回结果:{}", JSONObject.toJSONString(result));
        return R.success();
    }


    @Override
    public void drawCallback(DrawCallbackReq drawCallbackReq) {

    }
}
