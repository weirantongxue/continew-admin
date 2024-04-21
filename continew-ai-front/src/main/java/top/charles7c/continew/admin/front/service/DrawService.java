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

package top.charles7c.continew.admin.front.service;

import net.dreamlu.mica.core.result.R;
import top.charles7c.continew.admin.front.model.req.DrawCallbackReq;
import top.charles7c.continew.admin.front.model.req.DrawReq;
import top.charles7c.continew.admin.front.model.resp.DrawResp;
import top.charles7c.continew.admin.front.model.vo.DrawTaskVo;
import top.charles7c.continew.admin.front.model.vo.HistoricalImagesVo;
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.extension.crud.model.resp.PageResp;

/**
 * Created by WeiRan on 2024.03.14 17:53
 */
public interface DrawService {
    R<DrawTaskVo> createDrawTask(DrawReq drawReq);

    R<Object> checkDrawTask(String taskId);

    R<DrawResp> drawTask(String taskId);

    void drawCallback(DrawCallbackReq drawCallbackReq);

    R<PageResp<HistoricalImagesVo>> historicalImages(PageQuery pageQuery);
}
