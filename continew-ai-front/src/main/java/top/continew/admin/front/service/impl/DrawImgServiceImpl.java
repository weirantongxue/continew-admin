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

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.front.mapper.DrawImgMapper;
import top.continew.admin.front.model.entity.DrawImgDO;
import top.continew.admin.front.model.query.DrawImgQuery;
import top.continew.admin.front.model.req.DrawImgReq;
import top.continew.admin.front.model.resp.DrawImgDetailResp;
import top.continew.admin.front.model.resp.DrawImgResp;
import top.continew.admin.front.service.DrawImgService;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

/**
 * 绘图素材业务实现
 *
 * @author weiran
 * @since 2024/03/15 11:43
 */
@Service
@RequiredArgsConstructor
public class DrawImgServiceImpl extends BaseServiceImpl<DrawImgMapper, DrawImgDO, DrawImgResp, DrawImgDetailResp, DrawImgQuery, DrawImgReq> implements DrawImgService {}