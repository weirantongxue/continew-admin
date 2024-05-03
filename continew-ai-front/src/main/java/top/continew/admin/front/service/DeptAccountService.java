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

package top.continew.admin.front.service;

import top.continew.admin.front.model.entity.OrderInfoDO;
import top.continew.admin.front.model.query.DeptAccountQuery;
import top.continew.admin.front.model.req.DeptAccountReq;
import top.continew.admin.front.model.resp.DeptAccountDetailResp;
import top.continew.admin.front.model.resp.DeptAccountResp;
import top.continew.admin.front.model.vo.DeptAccountVo;
import top.continew.starter.extension.crud.service.BaseService;

/**
 * 部门账户业务接口
 *
 * @author weiran
 * @since 2024/04/01 18:42
 */
public interface DeptAccountService extends BaseService<DeptAccountResp, DeptAccountDetailResp, DeptAccountQuery, DeptAccountReq> {
    /**
     * 扣减余额
     * 
     * @param deptId
     */
    void deductBalance(Long deptId, int balanceToken);

    /**
     * 充值余额
     * 
     * @param orderInfo
     */
    void rechargeBalance(OrderInfoDO orderInfo);

    /**
     * 查询余额
     * 
     * @return
     */
    DeptAccountVo selectBalance(Long deptId);
}