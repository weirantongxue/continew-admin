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

package top.charles7c.continew.admin.system.service;

import top.charles7c.continew.admin.system.model.entity.DeptDO;
import top.charles7c.continew.admin.system.model.query.DeptQuery;
import top.charles7c.continew.admin.system.model.req.DeptReq;
import top.charles7c.continew.admin.system.model.resp.DeptDetailResp;
import top.charles7c.continew.admin.system.model.resp.DeptResp;
import top.charles7c.continew.starter.data.mybatis.plus.service.IService;
import top.charles7c.continew.starter.extension.crud.service.BaseService;

/**
 * 部门业务接口
 *
 * @author Charles7c
 * @since 2023/1/22 17:54
 */
public interface DeptService extends BaseService<DeptResp, DeptDetailResp, DeptQuery, DeptReq>, IService<DeptDO> {}
